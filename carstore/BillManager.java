package carstore;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BillManager {
    private static final String CHECKED_BILL_FILE_NAME = "Data/Receipt.txt";
    private static final String NOT_CHECKED_BILL_FILE_NAME = "Data/Bill.txt";
    private List<Bill> bills; // Unchecked bills
    private List<Bill> receipts; // Checked bills

    // Constructor
    public BillManager() {
        bills = new ArrayList<>();
        receipts = new ArrayList<>();
    }

    // Add a new bill
    public void createNewBill(Bill bill) {
        if (bill.isChecked()) {
            receipts.add(bill);
        } else {
            bills.add(bill);
        }
    }

    // Write bills to file
    public void writeToFile() {
        saveToFile(bills, NOT_CHECKED_BILL_FILE_NAME);
        saveToFile(receipts, CHECKED_BILL_FILE_NAME);
    }

    private void saveToFile(List<Bill> billList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Bill bill : billList) {
                writer.write(bill.toString());
            }
            System.out.println("Bills saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + fileName);
            e.printStackTrace();
        }
    }

    // Read bills from file
    public void readFromFile() {
        bills = loadFromFile(NOT_CHECKED_BILL_FILE_NAME);
        receipts = loadFromFile(CHECKED_BILL_FILE_NAME);
    }

    private List<Bill> loadFromFile(String fileName) {
    List<Bill> billList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 10) { // Ensure the correct number of fields
                String carName = parts[0];
                int carId = Integer.parseInt(parts[1]);
                int amount = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                double totalBalance = Double.parseDouble(parts[4]);
                LocalDateTime date = LocalDateTime.parse(parts[5], BillDetails.getFormatter());
                boolean isChecked = Boolean.parseBoolean(parts[6]);
                int billId = Integer.parseInt(parts[7]);
                String customerName = parts[8];
                int customerId = Integer.parseInt(parts[9]);

                // Create a Bill object
                Bill bill = new Bill(carName, carId, amount, price, totalBalance, date, isChecked, billId, customerName, customerId);
                billList.add(bill);
            } else {
                System.err.println("Malformed line in file: " + line);
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading from file: " + fileName);
        e.printStackTrace();
    } catch (Exception e) {
        System.err.println("Error parsing data: " + e.getMessage());
    }
    return billList;
}

    // Display unchecked bills to staff
    public void showUncheckedBillsToStaff(Scanner sc) {
        System.out.println("Unchecked Bills:");
        for (int i = 0; i < bills.size(); i++) {
            System.out.println((i + 1) + ". ");
            bills.get(i).showDetails();
            System.out.println("------");
        }

        if (bills.isEmpty()) {
            System.out.println("No unchecked bills available.");
            return;
        }

        System.out.print("Enter the number of the bill to check (0 to exit): ");
        int choice ;
        while(true){
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }
        break;
        }
        if (choice > 0 && choice <= bills.size()) {
            check(bills.get(choice - 1));
        } else {
            System.out.println("Invalid choice or no action taken.");
        }
    }

    // Staff checks a bill
    public void check(Bill bill) {
        if (!bill.isChecked()) {
            bill.check(); // Finalize the bill
            receipts.add(bill); // Move it to the receipts list
            bills.remove(bill); // Remove from the unchecked list
            System.out.println("Bill successfully checked.");
        } else {
            System.out.println("Bill is already checked.");
        }
    }

    
    private void statisticByMonth() {
    System.out.println("=== Statistics by Month ===");
    Map<String, Double> revenueByMonth = new HashMap<>();
    for (Bill bill : receipts) {
        String month = bill.getDate().getMonth().toString(); 
        revenueByMonth.put(month, revenueByMonth.getOrDefault(month, 0.0) + bill.getTotalBalance());
    }
    for (Map.Entry<String, Double> entry : revenueByMonth.entrySet()) {
        System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
    }
}


    private void statisticByDate(Scanner sc) {
    System.out.println("Enter start date (dd-MM-yyyy): ");
    String startDateStr = sc.nextLine();
    System.out.println("Enter end date (dd-MM-yyyy): ");
    String endDateStr = sc.nextLine();

    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        double totalRevenue = 0;
        System.out.println("Bills within the specified date range:");
        for (Bill bill : receipts) {
            LocalDate billDate = bill.getDate().toLocalDate();
            if (!billDate.isBefore(startDate) && !billDate.isAfter(endDate)) {
                bill.showDetails();
                totalRevenue += bill.getTotalBalance();
                System.out.println("------");
            }
        }
        System.out.printf("Total Revenue from %s to %s: $%.2f%n", startDate, endDate, totalRevenue);
    } catch (Exception e) {
        System.out.println("Invalid date format. Please use dd-MM-yyyy.");
    }
}


public void billManagerForStaff(Scanner sc) {
    boolean flag = true;
    readFromFile();
    do {
        System.out.println("\n=== Bill Management Menu ===");
        System.out.println("1. Show Unchecked Bills");
        System.out.println("2. Search for Bills");
        System.out.println("3. Statistic by months");
        System.out.println("4. Statistic by date");
        System.out.println("5. Save and exit");
        System.out.print("Enter your choice: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }
        sc.nextLine(); 

        switch (choice) {
            case 1:
                showUncheckedBillsToStaff(sc);
                break;

            case 2:
                search(sc);
                break;
            case 3:
                statisticByMonth();
                break;

            case 4:
                statisticByDate(sc);
                break;

            case 5:
                System.out.println("Exiting Bill Management. Goodbye!");
                writeToFile();
                flag = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    } while (flag);

    }
    
    public List<Bill> getBillByCustomerId(int customerId) {
        List<Bill> customerBills = new ArrayList<>();
        for (Bill bill : receipts) {
            if (bill.getCustomerId() == customerId) {
                customerBills.add(bill);
            }
        }
        for (Bill bill : bills) {
            if (bill.getCustomerId() == customerId) {
                customerBills.add(bill);
            }
        }
        return customerBills;
    }   

    public void search(Scanner sc){
        int choice;
        while(true){
        System.out.println("Search by: ");
        System.out.println("1.By id");
        System.out.println("2.By customer id");
        System.out.println("3.By date");
        System.out.print("Enter your choice: ");

        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }
        sc.nextLine(); 
        break;
        }

        switch (choice){
            case 1:
                System.out.println("Enter id: ");
                int id = sc.nextInt();
                Bill bill = getBillById(id);
                if(bill==null){
                    System.out.println("Bill not found");
                }else{
                    bill.showDetails();
                }
                break;
            case 2:
                System.out.println("Enter customer's id: ");
                int customerId = sc.nextInt();
                List<Bill> bills= getBillByCustomerId(customerId);
                if(bills.isEmpty()){
                    System.out.println("nothing to show");
                }else{
                    for (Bill thisbill : bills) {
                        thisbill.showDetails();
                        System.out.println("--------");
                    }
                }
                break;
            case 3:
                searchByDate(sc);
                break;
            default:
                break;
        }
    }

    private Bill getBillById(int id){
        for (Bill bill : receipts) {
            if(bill.getBillId()==id){
                return bill;
            }
        }
        return null;
    }

    private void searchByDate(Scanner sc) {
        System.out.print("Enter Date (dd-MM-yyyy): ");
        String dateStr = sc.nextLine();
    
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate searchDate = LocalDate.parse(dateStr, formatter);
    
            boolean found = false;
            for (Bill bill : receipts) {
                if (bill.getDate().toLocalDate().equals(searchDate)) {
                    bill.showDetails();
                    System.out.println("--------");
                    found = true;
                }
            }
    
            if (!found) {
                System.out.println("No bills found for the specified date.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy.");
        }
    }
}
