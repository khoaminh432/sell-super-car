package test;

import java.io.*;
import java.time.LocalDate;
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
    public void addBill(Bill bill) {
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
                writer.newLine();
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
                if (parts.length == 7) { // Ensure the correct number of fields
                    String customerName = parts[0];
                    int customerId = Integer.parseInt(parts[1]);
                    String carName = parts[2];
                    int carId = Integer.parseInt(parts[3]);
                    int amount = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    boolean isChecked = Boolean.parseBoolean(parts[6]);

                    Bill bill = new Bill(customerName, customerId, carName, carId, amount, price);
                    if (isChecked) {
                        bill.check(); // Finalize the bill if marked as checked
                    }
                    billList.add(bill);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + fileName);
            e.printStackTrace();
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
        int choice = sc.nextInt();
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
        String month = bill.getDate().getMonth().toString(); // Example: JANUARY
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
        System.out.println("2. Statistic by months");
        System.out.println("3. Statistic by date");
        System.out.println("4. Save and exit");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1:
                showUncheckedBillsToStaff(sc);
                break;

            case 2:
                statisticByMonth();
                break;

            case 3:
                statisticByDate(sc);
                break;

            case 4:
                System.out.println("Exiting Bill Management. Goodbye!");
                writeToFile();
                flag = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    } while (flag);

}
}
