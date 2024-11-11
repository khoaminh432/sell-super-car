package carstore;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{

    private List<String> purchaseHistory;

    public Customer() {
    }

    public Customer(int id, String name, String email, String password, String contactNumber, Location address) {
        super(id, name, email, password, contactNumber, address);
        purchaseHistory=new ArrayList<>();
    }

    //getter and setter
    public void addPurchase(String purchaseDetail) {
        purchaseHistory.add(purchaseDetail);
    }

    public List<String> getPurchaseHistory() {
        return purchaseHistory;
    }

    @Override
    public void showDetails(){
        super.showDetails();
        System.out.println("Purchase Histrory: ");
        for(String purchase:purchaseHistory){
            System.out.println("- "+purchase);
        }
    }

    @Override
    public String toString(){
        if(!purchaseHistory.isEmpty()){
            String purchaseString=String.join("|",purchaseHistory);
            return super.toString()+"\t"+purchaseString;
        }
        return super.toString();
    }


}
