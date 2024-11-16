package carstore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Admin extends Person {
    private static final String ADMIN_FILE = "Data/Admin.txt";

    // Constructor
    public Admin() {
        readAdminInfoFromFile();
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
                this.setEmail(password);
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

                System.out.println("Admin loaded: " + this.getName());
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
}

