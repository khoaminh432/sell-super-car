package carstore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BillDetails {
    private String carName;
    private int carId;
    private int amount;
    private double price;
    private double totalBalance;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private LocalDateTime date;
    private boolean isChecked;

    // Constructor
    public BillDetails(String carName, int carId, int amount, double price) {
        this.carName = carName;
        this.carId = carId;
        this.amount = amount;
        this.price = price;
        this.totalBalance = price*amount;
        date = LocalDateTime.now();
        isChecked = false;
    }

    public BillDetails(String carName, int carId, int amount, double price, double totalBalance, LocalDateTime date,
            boolean isChecked) {
        this.carName = carName;
        this.carId = carId;
        this.amount = amount;
        this.price = price;
        this.totalBalance = totalBalance;
        this.date = date;
        this.isChecked = isChecked;
    }

    //getter and setter
    private String showIsChecked() {
        if (isChecked) {
            return "Your order was accepted by staff";
        } else {
            return "In progress...";
        }
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void check() {
        this.isChecked = true;
    }

    public void showDetails() {
        System.out.println("Date: " + (date == null ? "N/A" : date.format(formatter)));
        System.out.println("Status: " + showIsChecked());
        System.out.println("Car Name: " + carName);
        System.out.println("Car ID: " + carId);
        System.out.println("Amount: " + amount);
        System.out.printf("Price: %.2f\n", price);
        System.out.printf("Total Balance: %.2f\n", totalBalance);
    }

    public String toString() {
        return carName +  "," + carId + "," + amount + "," + totalBalance + "," + date.format(formatter) + "," + isChecked;
    }
}