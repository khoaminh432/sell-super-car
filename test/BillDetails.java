package carstore;



public class BillDetails {
    private String carName;
    private int carId;
    private int amount;
    private double price;
    private double totalBalance;

    // Constructor
    public BillDetails(String carName, int carId, int amount, double price) {
        this.carName = carName;
        this.carId = carId;
        this.amount = amount;
        this.price = price;
        this.totalBalance = price*amount;
    }

    //getter and setter
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
}