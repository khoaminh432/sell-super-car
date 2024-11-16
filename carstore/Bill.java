package carstore;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import department_car.List_Car;
import department_car.Super_car;

public class Bill {
    private int billId;
    private static int billNextId = 1;
    private BillDetails billDetails;
    private Customer customer;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private LocalDateTime date; 
    private boolean isChecked;
    
    
    public Bill(Customer customer) {
        billId = billNextId++;
        billDetails = new BillDetails();
        this.customer = customer;
        isChecked = false;

    }

    public void addItem(Super_car car, Scanner sc) {
        if (isChecked) {
            System.out.println("The bill is finalized and cannot be modified.");
            return;
        }

        int amount;
        do {
            System.out.println("Enter the amount: ");
            amount = sc.nextInt();
            if (amount > car.getQuantityof_car()) {
                System.out.println("The amount must not exceed the car's available quantity.");
                System.out.println("Available quantity: " + car.getQuantityof_car());
            } else if (amount < 1) {
                System.out.println("The amount must be at least 1.");
            } else {
                break; 
            }
        } while (true);

        billDetails.addOrUpdateCar(car, amount);
    }

    public void deleteItem(Scanner sc) {
        if (isChecked) {
            System.out.println("The bill is finalized and cannot be modified.");
            return;
        }

        List<Super_car> cars = billDetails.getDetailsBeforeChecked();
        if (cars.isEmpty()) {
            System.out.println("No items in the bill to delete.");
            return;
        }

        System.out.println("Enter the item number to delete: ");
        int itemIndex = sc.nextInt();
        if (itemIndex < 0 || itemIndex >= cars.size()) {
            System.out.println("Invalid item number.");
            return;
        }

        Super_car carToDelete = cars.get(itemIndex);
        billDetails.removeCar(carToDelete);
        System.out.println("Item removed successfully.");
    }

    public void check() {
        this.isChecked = true;
        this.date = LocalDateTime.now();
        System.out.println("Bill finalized on: " + date.format(formatter));
    }

    public void showDetail() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Customer: " + customer.getName() + " (ID: " + customer.getID() + ")");
        System.out.println("Bill Date: " + (date != null ? date.format(formatter) : "Not finalized yet"));

        if (isChecked) {
            billDetails.showDetailsAfterChecked();
        } else {
            billDetails.getDetailsBeforeChecked();
        }
        System.out.println("Total Balance: " + getTotalBalance());
    }

    // 
    public double getTotalBalance() {
        return billDetails.getTotalBalance();
    }

    public int getTotalAmount(){
        return billDetails.getTotalAmount();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getBillId() {
        return billId;
    }

    public Customer getCustomer() {
        return customer;
    }


    // Format the Bill into a string for file storage
    public String formatBillForFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(billId).append("|");
        sb.append(customer.getID()).append("|");
        sb.append(isChecked).append("|");
        sb.append(getTotalBalance()).append("|");

        for (Map.Entry<Super_car, Integer> entry : billDetails.getCarSold().entrySet()) {
            Super_car car = entry.getKey();
            int amount = entry.getValue();
            sb.append(car.getId_car()).append(":").append(amount).append(",");
        }
        return sb.toString();
    }

    // Accessor for BillDetails
    public BillDetails getBillDetails() {
        return billDetails;
    }
}
