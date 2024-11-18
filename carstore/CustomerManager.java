package carstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: lyhieunghia
 */

public class CustomerManager implements IFeatures<Customer> {
    private List<Customer> cList;
    private Scanner sc;
    private IdManager idCustomer;
    private static final String Customer_FILE_NAME="Data/Customer.txt";
    private static final String Customer_ID_FILE_NAME="Data/customerIdData.txt";
    
        //Constructor
    public CustomerManager(){
        cList = new ArrayList<>();
        sc = new Scanner(System.in);
        idCustomer = new IdManager(Customer_ID_FILE_NAME);
    }

    /* Display the Customer List:BEGIN
     * parameter: nothing
     * return: nothing
     * Description: -show the list of the customers
     */
    @Override
    public void display(){
        if(cList.isEmpty()){
            System.out.println("Nothing to display.");
            return;
        }

        System.out.println("Customer List:");
        for (int i = 0; i < cList.size(); i++) {
            System.out.println("----------------------------------");
            System.out.println((i + 1) + ". ");  
            cList.get(i).showDetails();
        }
    }
    //Display: END

    /* ReadFromFile:BEGIN
     * parameter:nothing
     * return: nothing
     * Description: -read the customer list from "Customer.txt"
     */
    @Override
    public void readFromFile() {
        File file = new File(Customer_FILE_NAME);  
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line == ""){
                    continue;
                }
                String[] info = line.split("\t");
    
                // Parsing customer information
                int id = Integer.parseInt(info[0].trim());
                String name = info[1].trim();
                String email = info[2].trim();
                String password = info[4].trim();
                String contact = info[3].trim();
    
                // Parsing address
                String houseNumber = info[5].trim();
                String street = info[6].trim();
                String ward = info[7].trim();
                String district = info[8].trim();
                String city = info[9].trim();
                Location address = new Location(houseNumber, street, ward, district, city);
    
                // Creating customer and adding purchase history
                Customer customer = new Customer(id, name, email, password, contact, address);
                
                cList.add(customer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customer file not found. Creating a new one.");
            try {
                if (file.createNewFile()) {
                    System.out.println("New customer file created: " + Customer_FILE_NAME);
                }
            } catch (IOException ex) {
                System.out.println("Error creating new customer file: " + ex.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        
    }
    //ReadFromFile:END

    /*
     * 
     * 
     * 
     */
    @Override
    public void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Customer_FILE_NAME))) {
            for(Customer c:cList){
                bw.write(c.toString());
            }
            System.out.println("Customer data saved saves successfully");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }
    //WriteToFile:END

    @Override
    //Add new customer
    public boolean add(){
        System.out.println("--------------Add new customer--------------");
        String name = ClientValidator.isNameValid(sc);
        String email = ClientValidator.isEmailAdressValid(sc);

        //check if email is already in use
        if (isEmailAlreadyUsed(email)) {
            System.out.println("This email is already registered. Please try a different email.");
            return false; //Exit the method if email is not unique
        }

        String password = ClientValidator.isPasswordValid(sc);
        String contact = ClientValidator.isContactNumberValid(sc);

        // Check if the contact number is already in use
        if (isContactNumberAlreadyUsed(contact)) {
            System.out.println("This contact number is already registered. Please try a different contact number.");
            return false;  // Exit the method if contact number is not unique
        }

        //address
        System.out.println("House Number: ");
        String houseNumber = sc.nextLine().trim();
        System.out.println("Street: ");
        String street = sc.nextLine().trim();
        System.out.println("Ward: ");
        String ward = sc.nextLine().trim();
        System.out.println("District: ");
        String district = sc.nextLine().trim();
        System.out.println("City: ");
        String city = sc.nextLine().trim();

        int id = idCustomer.idGenerator();
        Location address = new Location(houseNumber,street,ward,district,city);
        Customer newCustomer = new Customer(id, name, email, password, contact, address);

        cList.add(newCustomer);
        System.out.println("New customer created successfully with ID: " + id);
        cList.sort((c1, c2) -> Integer.compare(c1.getID(), c2.getID()));
        //writeToFile();
        //idCustomer.writeIDsToFile(Customer_ID_FILE_NAME);
        
        return true;
    }

    private boolean isEmailAlreadyUsed(String email) {
        for (Customer customer : cList) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContactNumberAlreadyUsed(String contact) {
        for (Customer customer : cList) {
            if (customer.getContactNumber().equals(contact)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Customer toDeleteCustomer) {
        if (toDeleteCustomer != null && cList.remove(toDeleteCustomer)) {
            System.out.println("Customer member with ID " + toDeleteCustomer.getID() + " deleted successfully.");
        } else {
            System.out.println("Customer member not found.");
        }
    }

    @Override
    public Customer search() {
        System.out.println("Search by: ");
        System.out.println("1. Customer ID");
        System.out.println("2. Name");
        System.out.println("3. Email");
        System.out.print("Enter your choice (1-3): ");
        int choice = sc.nextInt();
        sc.nextLine(); 
    
        List<Customer> searchResults = new ArrayList<>();
    
        switch (choice) {
            case 1:
                // Search by id
                System.out.print("Enter customer ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                searchResults.add(getCustomerById(id));
                break;
    
            case 2:
                // Search by Name
                System.out.print("Enter customer name (or part of the name): ");
                String name = sc.nextLine().trim().toLowerCase();
                for (Customer customer : cList) {
                    if (customer.getName().toLowerCase().contains(name)) {
                        searchResults.add(customer);
                    }
                }
                break;
    
            case 3:
                // Search by Email
                System.out.print("Enter customer email: ");
                String email = ClientValidator.isEmailAdressValid(sc);
                for (Customer customer : cList) {
                    if (customer.getEmail().equalsIgnoreCase(email)) {
                        searchResults.add(customer);
                    }
                }
                break;
    
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                return null;
        }
    
        // Display search results
        if (searchResults.isEmpty()) {
            System.out.println("No customers found matching your search criteria.");
            return null;
        }
    
        System.out.println("Search Results:");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println((i + 1) + ". ");  
            searchResults.get(i).showDetails();
        }
    
        // Allow the user to select a customer from the search results
        System.out.print("Enter the number of the customer to select (or 0 to cancel): ");
        int selection = sc.nextInt();
        sc.nextLine();  // Consume the newline character
    
        if (selection > 0 && selection <= searchResults.size()) {
            Customer selectedCustomer = searchResults.get(selection - 1);
            System.out.println("You selected: " + selectedCustomer.getName());
            return selectedCustomer;
        } else {
            System.out.println("Selection cancelled or invalid.");
            return null;
        }
    }
    
    @Override
    public void update(Customer customerToUpdate) {
        System.out.println("------- Update Your Information -------");
        System.out.println("Updating details for " + customerToUpdate.getName() + "...");
    
        boolean keepUpdating = true;
        while (keepUpdating) {
            System.out.println("\nSelect the field to update:");
            System.out.println("1. Name");
            System.out.println("2. Email");
            System.out.println("3. Contact Number");
            System.out.println("4. Address");
            System.out.println("5. Finish updating");
    
            System.out.print("Enter your choice (1-5): ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline
    
            switch (choice) {
                case 1:
                    updateName(customerToUpdate);
                    break;
                case 2:
                    updateEmail(customerToUpdate);
                    break;
                case 3:
                    updateContactNumber(customerToUpdate);
                    break;
                case 4:
                    updateAddress(customerToUpdate);
                    break;
                case 5:
                    System.out.println("Finished updating.");
                    keepUpdating = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
    
    private void updateName(Customer customer) {
        System.out.println("Current Name: " + customer.getName());
        String newName = ClientValidator.isNameValid(sc);
        customer.setName(newName);
        System.out.println("Name updated successfully!");
    }
    
    private void updateEmail(Customer customer) {
        System.out.println("Current Email: " + customer.getEmail());
        String newEmail = ClientValidator.isEmailAdressValid(sc);
        customer.setEmail(newEmail);
        System.out.println("Email updated successfully!");
    }
    
    private void updateContactNumber(Customer customer) {
        System.out.println("Current Contact Number: " + customer.getContactNumber());
        String newContact = ClientValidator.isContactNumberValid(sc);
        customer.setContactNumber(newContact);
        System.out.println("Contact Number updated successfully!");
    }
    
    private void updateAddress(Customer customer) {
        System.out.println("Updating Address:");
        System.out.println("Current Address: " + customer.getAddress().toString());
    
        System.out.print("Enter House Number: ");
        String houseNumber = sc.nextLine().trim();
        System.out.print("Enter Street: ");
        String street = sc.nextLine().trim();
        System.out.print("Enter Ward: ");
        String ward = sc.nextLine().trim();
        System.out.print("Enter District: ");
        String district = sc.nextLine().trim();
        System.out.print("Enter City: ");
        String city = sc.nextLine().trim();
    
        Location newAddress = new Location(houseNumber, street, ward, district, city);
        customer.setAddress(newAddress);
        System.out.println("Address updated successfully!");
    }

    //Save data after changes
    //Called by customer manage menu
    public void saveData(){
        writeToFile();
        if(idCustomer.writeIDsToFile(Customer_FILE_NAME)){
            System.out.println("Customer id file saved successfully");
        }
    }

    //Customer login
    //return the customer with correct email and password
    public Customer login(String email, String password){
        for (Customer customer : cList) {
            if(customer.login(email, password)){
                return customer;
            }
        }
        return null;
    }

    //Check if an email exists for customer's password recovery
    public Customer searchByEmailCaseSensitive(String email){
        for (Customer customer : cList) {
            if(customer.getEmail().equals(email)){
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomerById(int id){
        Customer result = new Customer();
        for (Customer customer : cList) {
            if(customer.getID()==id){
                result = customer;
            }
        }
        return result;
    }
    /**
    * Sort the customer list by name in alphabetical order.
    */
    public void sortByName() {
        if (cList.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to sort.");
            return;
        }
        cList.sort((customer1, customer2) -> customer1.getName().compareToIgnoreCase(customer2.getName()));
        System.out.println("Customer list sorted by name successfully.");
    }

    /**
    * Sort the customer list by ID in ascending order.
    */
    public void sortById() {
        if (cList.isEmpty()) {
            System.out.println("Customer list is empty. Nothing to sort.");
            return;
        }
        cList.sort((customer1, customer2) -> Integer.compare(customer1.getID(), customer2.getID()));
        System.out.println("Customer list sorted by ID successfully.");
    }

    
    public void customerManagementMenu(Scanner sc) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Customer Management Menu ===");
            System.out.println("1. Display all customers");
            System.out.println("2. Add new customer");
            System.out.println("3. Update customer details");
            System.out.println("4. Delete customer");
            System.out.println("5. Search customer");
            System.out.println("6. Sort customers by name");
            System.out.println("7. Sort customers by ID");
            System.out.println("8. Save data and exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
    
            switch (choice) {
                case 1:
                    display();
                    break;
                case 2:
                    if (add()) {
                        System.out.println("Customer added successfully!");
                    }
                    break;
                case 3:
                    Customer customerToUpdate = search();
                    if (customerToUpdate != null) {
                        update(customerToUpdate);
                    } else {
                        System.out.println("No customer selected for update.");
                    }
                    break;
                case 4:
                    Customer customerToDelete = search();
                    if (customerToDelete != null) {
                        delete(customerToDelete);
                    } else {
                        System.out.println("No customer selected for deletion.");
                    }
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    sortByName();
                    display();
                    break;
                case 7:
                    sortById();
                    display();
                    break;
                case 8:
                    System.out.println("Saving data and exiting...");
                    saveData();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    
}
    