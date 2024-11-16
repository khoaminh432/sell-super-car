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
    public void display(){
        if(cList.isEmpty()){
            System.out.println("Nothing to display.");
            return;
        }

        for(Customer customer:cList){
            System.out.println("------------------------------------");
            customer.showDetails();
            System.out.println("------------------------------------");
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
                String[] info = line.split("\t");
    
                // Parsing customer information
                int id = Integer.parseInt(info[0].trim());
                String name = info[1].trim();
                String email = info[2].trim();
                String password = info[3].trim();
                String contact = info[4].trim();
    
                // Parsing address
                String houseNumber = info[5].trim();
                String street = info[6].trim();
                String ward = info[7].trim();
                String district = info[8].trim();
                String city = info[9].trim();
                Location address = new Location(houseNumber, street, ward, district, city);
    
                // Creating customer and adding purchase history
                Customer customer = new Customer(id, name, email, password, contact, address);
                if (info.length > 10) {
                    String[] purchases = info[10].split("\\|");
                    for (String purchase : purchases) {
                        customer.addPurchase(purchase.trim());
                    }
                }
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
                bw.newLine();
            }
            System.out.println("Customer data saved saves successfully");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }
    //WriteToFile:END

    public void add(){
        System.out.println("--------------Add new customer--------------");
        String name = ClientValidator.isNameValid(sc);
        String email = ClientValidator.isEmailAdressValid(sc);
        String password = ClientValidator.isPasswordValid(sc);
        String contact = ClientValidator.isContactNumberValid(sc);

        String houseNumber = sc.nextLine().trim();
        String street = sc.nextLine().trim();
        String ward = sc.nextLine().trim();
        String district = sc.nextLine().trim();
        String city = sc.nextLine().trim();

        int id = idCustomer.idGenerator();
        Location address = new Location(houseNumber,street,ward,district,city);
        Customer newCustomer = new Customer(id, name, email, password, contact, address);

        cList.add(newCustomer);
    }

    @Override
    public void delete(Customer toDeleteCustomer) {
        cList.remove(toDeleteCustomer);
    }
    

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
                for (Customer customer : cList) {
                    if (customer.getID() == id) {
                        searchResults.add(customer);
                    }
                }
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
                String email = sc.nextLine().trim();
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
    

    public void update(){

    }

    public void saveData(){
        writeToFile();
        if(idCustomer.writeIDsToFile(Customer_FILE_NAME)){
            System.out.println("Customer id file saved successfully");
        }
    }
}
    