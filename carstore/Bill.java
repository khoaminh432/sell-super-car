package carstore;

import java.time.LocalDateTime;

public class Bill extends BillDetails {
    private int billId;
    private static int billNextId = 1;
    private String customerName;
    private int customerId;
    
    //constructor
    public Bill(String carName, int carId, int amount, double price, double totalBalance, LocalDateTime date,
        boolean isChecked, int billId, String customerName, int customerId) {
        super(carName, carId, amount, price, totalBalance, date, isChecked);
        this.billId = billId;
        this.customerName = customerName;
        this.customerId = customerId;
        billNextId++;
    }

    public Bill(String carName, int carId, int amount, double price, String customerName, int customerId) {
        super(carName, carId, amount, price);
        billId = billNextId++;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    //getter and setter
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public static int getBillNextId() {
        return billNextId;
    }

    public static void setBillNextId(int billNextId) {
        Bill.billNextId = billNextId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public void showDetails(){
        System.out.println("Bill ID: " + billId);
        System.out.println("Customer: " + customerName + "\tID: " + customerId);
        super.showDetails();
    }

    public void showBillDetails(){
        super.showDetails();
    }

    @Override
    public String toString() {
        return super.toString() + "\t" + billId + "\t" + customerName + "\t" + customerId;
    }

}