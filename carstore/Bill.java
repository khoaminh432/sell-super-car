package carstore;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Bill extends BillDetails {
    private int billId;
    private static int billNextId = 1;
    private String customerName;
    private int customerId;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private LocalDateTime date;
    private boolean isChecked;
    
    //Contructor
    public Bill(String customerName, int customerId, String carName, int carId, int amount, double price) {
        super(carName, carId, amount, price);
        billId = billNextId++;
        this.customerId = customerId;
        this.customerName = customerName;
        isChecked = false;
        date = LocalDateTime.now();
    }

    

    public int getBillId() {
        return billId;
    }

    public static int getBillNextId() {
        return billNextId;
    }

    public String getCustomerName(){
        return customerName;
    }

    public int getCustomerId(){
        return customerId;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void check() {
        if (!isChecked) {
            this.date = LocalDateTime.now();
            this.isChecked = true;
            System.out.println("Bill checked and finalized on: " + date.format(formatter));
        } else {
            System.out.println("Bill is already checked.");
        }
    }

    public void showDetails(){
        System.out.println("Bill ID: " + billId);
        System.out.println("Customer: " + customerName + "\tID: " + customerId);
        super.showDetails();
        System.out.println("Date: " + date.format(formatter));
        showIsChecked();
    }

    private void showIsChecked() {
        if (isChecked) {
            System.out.println("Status: Your order was accepted by staff");
        } else {
            System.out.println("Status: In progress...");
        }
    }

}