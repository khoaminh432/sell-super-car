package carstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends Person{
    private List<Bill> purchaseHistory;

    public Customer() {
    }

    public Customer(int id, String name, String email, String password, String contactNumber, Location address) {
        super(id, name, email, password, contactNumber, address);
        purchaseHistory=new ArrayList<>();
    }

    //getter and setter
    public void addPurchase(Bill purchaseDetail) {
        purchaseHistory.add(purchaseDetail);
    }

    public List<Bill> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void readPurchaseHistory(BillManager billManager){
        purchaseHistory=billManager.getBillByCustomerId(getID());
    }

    public void showPurchaseHistory(){
        for (Bill bill : purchaseHistory) {
            bill.showBillDetails();
            System.out.println("---------");
        }
    }

    @Override
    public void showDetails(){
        super.showDetails();
    }

    @Override
    public String toString(){
        return super.toString()+"\n";
    }

    @Override
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
        System.out.println("------Customer Password Recovery------");
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
