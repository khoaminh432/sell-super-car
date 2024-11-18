package carstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Admin extends Person {
    private static final String ADMIN_FILE = "Data/Admin.txt";

    // Constructor
    public Admin() {
        readAdminInfoFromFile();
    }

    public void saveData(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_FILE))) {
            bw.write(super.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void readAdminInfoFromFile() {
        File file = new File(ADMIN_FILE);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split("\t");

                // Parsing admin information
                int id = Integer.parseInt(info[0].trim());
                this.setID(id);
                String name = info[1].trim();
                this.setName(name);
                String email = info[2].trim();
                this.setEmail(email);
                String password = info[3].trim();
                this.setPassword(password);
                String contact = info[4].trim();
                this.setContactNumber(contact);

                // Parsing address
                String houseNumber = info[5].trim();
                String street = info[6].trim();
                String ward = info[7].trim();
                String district = info[8].trim();
                String city = info[9].trim();
                Location address = new Location(houseNumber, street, ward, district, city);
                this.setAddress(address);

                //System.out.println("Admin loaded: " + this.getName());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Admin file not found. Creating a new one.");
            try {
                if (file.createNewFile()) {
                    System.out.println("New admin file created: " + ADMIN_FILE);
                }
            } catch (IOException ex) {
                System.out.println("Error creating new admin file: " + ex.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading admin file: " + e.getMessage());
        }
    }

    public boolean login(String email, String password){
        if(this.getEmail().equals(email)){
            if (this.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changePassword(Scanner sc){
        System.out.println("------Admin Password Recovery------");
        System.out.println("Create a new password: ");
        String newPassword = ClientValidator.isPasswordValid(sc);
        System.out.println("Confirm your password: ");
        String confirmPassword = ClientValidator.isPasswordValid(sc);
        if(newPassword==confirmPassword){
           setPassword(newPassword);
           System.out.println("Password changed successfully.");
        }
   }
}

