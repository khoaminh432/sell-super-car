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
 * Author: lyhieunghia
 */

public class StaffManager implements IFeatures<Staff> {
    private List<Staff> sList;
    private Scanner sc;
    private IdManager idStaff;
    private static final String STAFF_FILE_NAME = "Data/Staff.txt";
    private static final String STAFF_ID_FILE_NAME = "Data/staffIdData.txt";

    // Constructor
    public StaffManager() {
        sList = new ArrayList<>();
        sc = new Scanner(System.in);
        idStaff = new IdManager(STAFF_ID_FILE_NAME);
        readFromFile();
    }

    /**
     * Display the Staff List
     */
    @Override
    public void display() {
        if (sList.isEmpty()) {
            System.out.println("Nothing to display.");
            return;
        }

        System.out.println("Staff List:");
        for (int i = 0; i < sList.size(); i++) {
            System.out.println("---------------------------------------");
            System.out.println((i + 1) + ". ");  
            sList.get(i).showDetails();
        }
    }

    /**
     * Read staff data from file
     */
    @Override
    public void readFromFile() {
        File file = new File(STAFF_FILE_NAME);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            sList.clear(); // Clear existing list before reading
            while ((line = br.readLine()) != null) {
                String[] info = line.split("\t");

                // Ensure there are enough fields
                if (info.length < 7) {
                    System.out.println("Invalid staff entry: " + line);
                    continue;
                }

                // Parsing staff information
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

                // Parsing salary
                double salary = Double.parseDouble(info[10].trim());

                // Creating staff object
                Staff staff = new Staff(id, name, email, password, contact, address, salary);
                sList.add(staff);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Staff file not found. Creating a new one.");
            try {
                if (file.createNewFile()) {
                    System.out.println("New staff file created: " + STAFF_FILE_NAME);
                }
            } catch (IOException ex) {
                System.out.println("Error creating new staff file: " + ex.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading staff file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from staff file: " + e.getMessage());
        }
    }

    /**
     * Write staff data to file
     */
    @Override
    public void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STAFF_FILE_NAME))) {
            for (Staff s : sList) {
                bw.write(s.toString());
            }
            System.out.println("Staff data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to staff file: " + e.getMessage());
        }
    }

    /**
     * Add a new staff member
     */
    @Override
    public boolean add() {
        System.out.println("--------------Add New Staff--------------");
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

        System.out.println("Enter address details:");
        System.out.print("House Number: ");
        String houseNumber = sc.nextLine().trim();
        System.out.print("Street: ");
        String street = sc.nextLine().trim();
        System.out.print("Ward: ");
        String ward = sc.nextLine().trim();
        System.out.print("District: ");
        String district = sc.nextLine().trim();
        System.out.print("City: ");
        String city = sc.nextLine().trim();

        double salary = ClientValidator.isSalaryValid(sc);

        int id = idStaff.idGenerator();
        Location address = new Location(houseNumber, street, ward, district, city);
        Staff newStaff = new Staff(id, name, email, password, contact, address, salary);

        sList.add(newStaff);
        System.out.println("New staff member added successfully with ID: " + id);
        sList.sort((c1, c2) -> Integer.compare(c1.getID(), c2.getID()));


        return true;
    }

    private boolean isEmailAlreadyUsed(String email) {
        for (Staff staff : sList) {
            if (staff.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContactNumberAlreadyUsed(String contact) {
        for (Staff staff : sList) {
            if (staff.getContactNumber().equals(contact)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a staff member
     */
    @Override
    public void delete(Staff toDeleteStaff) {
        if (toDeleteStaff != null && sList.remove(toDeleteStaff)) {
            System.out.println("Staff member with ID " + toDeleteStaff.getID() + " deleted successfully.");
        } else {
            System.out.println("Staff member not found.");
        }
    }

    /**
     * Search for a staff member
     */
    @Override
    public Staff search() {
        System.out.println("Search by: ");
        System.out.println("1. Staff ID");
        System.out.println("2. Name");
        System.out.println("3. Email");
        System.out.print("Enter your choice (1-3): ");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            return null;
        }

        List<Staff> searchResults = new ArrayList<>();

        switch (choice) {
            case 1:
                // Search by ID
                System.out.print("Enter staff ID: ");
                int id;
                try {
                    id = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format.");
                    return null;
                }
                for (Staff staff : sList) {
                    if (staff.getID() == id) {
                        searchResults.add(staff);
                    }
                }
                break;

            case 2:
                // Search by Name
                System.out.print("Enter staff name (or part of the name): ");
                String name = sc.nextLine().trim().toLowerCase();
                for (Staff staff : sList) {
                    if (staff.getName().toLowerCase().contains(name)) {
                        searchResults.add(staff);
                    }
                }
                break;

            case 3:
                // Search by Email
                System.out.print("Enter staff email: ");
                String email = sc.nextLine().trim().toLowerCase();
                for (Staff staff : sList) {
                    if (staff.getEmail().toLowerCase().equals(email)) {
                        searchResults.add(staff);
                    }
                }
                break;

            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                return null;
        }

        // Display search results
        if (searchResults.isEmpty()) {
            System.out.println("No staff members found matching your search criteria.");
            return null;
        }

        System.out.println("Search Results:");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println((i + 1) + ". ");
            searchResults.get(i).showDetails();
            System.out.println("------------------------------------");
        }

        // Allow the user to select a staff member from the search results
        System.out.print("Enter the number of the staff member to select (or 0 to cancel): ");
        int selection;
        try {
            selection = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid selection.");
            return null;
        }

        if (selection > 0 && selection <= searchResults.size()) {
            Staff selectedStaff = searchResults.get(selection - 1);
            System.out.println("You selected: " + selectedStaff.getName());
            return selectedStaff;
        } else {
            System.out.println("Selection cancelled or invalid.");
            return null;
        }
    }

    /**
     * Update a staff member's details
     */
    @Override
    public void update(Staff staffToUpdate) {
        System.out.println("------- Update Staff Details -------");
        System.out.println("Updating details for " + staffToUpdate.getName() + "...");
    
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
            sc.nextLine(); 
    
            switch (choice) {
                case 1:
                    updateName(staffToUpdate);
                    break;
                case 2:
                    updateEmail(staffToUpdate);
                    break;
                case 3:
                    updateContactNumber(staffToUpdate);
                    break;
                case 4:
                    updateAddress(staffToUpdate);  // New case for updating address
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
    
    private void updateName(Staff staffToUpdate) {
        System.out.println("Current Name: " + staffToUpdate.getName());
        String newName = ClientValidator.isNameValid(sc);
        staffToUpdate.setName(newName);
        System.out.println("Name updated successfully!");
    }
    
    private void updateEmail(Staff staffToUpdate) {
        System.out.println("Current Email: " + staffToUpdate.getEmail());
        String newEmail = ClientValidator.isEmailAdressValid(sc);
        staffToUpdate.setEmail(newEmail);
        System.out.println("Email updated successfully!");
    }
    
    private void updateContactNumber(Staff staffToUpdate) {
        System.out.println("Current Contact Number: " + staffToUpdate.getContactNumber());
        String newContact = ClientValidator.isContactNumberValid(sc);
        staffToUpdate.setContactNumber(newContact);
        System.out.println("Contact Number updated successfully!");
    }

    private void updateAddress(Staff staffToUpdate) {
        System.out.println("Updating Address:");
        System.out.println("Current Address: " + staffToUpdate.getAddress().toString());
    
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
        staffToUpdate.setAddress(newAddress);
        System.out.println("Address updated successfully!");
    }

    /*
     * update salary methods for admin to change staff's salary
     */
    public void updateSalary(Staff staffToUpdate) {
        System.out.println("Current Salary: " + staffToUpdate.getSalary());
        double newSalary = ClientValidator.isSalaryValid(sc);
        staffToUpdate.setSalary(newSalary);
        System.out.println("Salary updated successfully!");
    }

    /*
     * Staff login
     */

    public Staff login(String email, String password){
        for (Staff staff : sList) {
            if(staff.login(email,password)){
                return staff;
            }
        }
        return null;
    }

    
    /**
     * Save data to files
     */
    public void saveData() {
        writeToFile();
        if (idStaff.writeIDsToFile(STAFF_ID_FILE_NAME)) {
            System.out.println("Staff ID file saved successfully.");
        }
    }

    public Staff searchByEmailCaseSensitive(String email){
        for (Staff staff : sList) {
            if(staff.getEmail().equals(email)){
                return staff;
            }
        }
        return null;
    }

    public void sortByName() {
    if (sList.isEmpty()) {
        System.out.println("Staff list is empty. Nothing to sort.");
        return;
    }
    sList.sort((staff1, staff2) -> staff1.getName().compareToIgnoreCase(staff2.getName()));
    System.out.println("Staff list sorted by name successfully.");
    }
    /**
    * Sort the staff list by ID in ascending order.
    */
    public void sortById() {
        if (sList.isEmpty()) {
            System.out.println("Staff list is empty. Nothing to sort.");
            return;
        }
        sList.sort((staff1, staff2) -> Integer.compare(staff1.getID(), staff2.getID()));
        System.out.println("Staff list sorted by ID successfully.");
    }

    public void staffManagementMenu(Scanner sc) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Staff Management Menu ===");
            System.out.println("1. Display all staff");
            System.out.println("2. Add new staff");
            System.out.println("3. Update staff details");
            System.out.println("4. Delete staff");
            System.out.println("5. Search staff");
            System.out.println("6. Update staff salary");
            System.out.println("7. Sort staff by name");
            System.out.println("8. Sort staff by ID");
            System.out.println("9. Save and exit");
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
                        System.out.println("Staff added successfully!");
                    }
                    break;
                case 3:
                    Staff staffToUpdate = search();
                    if (staffToUpdate != null) {
                        update(staffToUpdate);
                    } else {
                        System.out.println("No staff selected for update.");
                    }
                    break;
                case 4:
                    Staff staffToDelete = search();
                    if (staffToDelete != null) {
                        delete(staffToDelete);
                    } else {
                        System.out.println("No staff selected for deletion.");
                    }
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    Staff staffToAdjustSalary = search();
                    if (staffToAdjustSalary != null) {
                        updateSalary(staffToAdjustSalary);
                    } else {
                        System.out.println("No staff selected for salary update.");
                    }
                    break;
                case 7:
                    sortByName();
                    display();
                    break;
                case 8:
                    sortById();
                    display();
                    break;
                case 9:
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
