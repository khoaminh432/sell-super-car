
import carstore.Customer;


public class bill {
    private int id_bill = 0;
    private Customer myCustomer;
    private Super_car cus_Super_car;
    private int quantityordercar;
    public static int quantitybill = 0;

    public bill(){
        id_bill++;
        quantitybill++;
        quantityordercar = 0;
    }
    public bill(Customer myCustomer,Super_car cSuper_car,int quantityordercar){
        id_bill++;
        quantitybill++;
        this.myCustomer = myCustomer;
        cus_Super_car = cSuper_car;
        this.quantityordercar = quantityordercar;
    }
    public int getId_bill() {
        return id_bill;
    }
    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }
    // tổng số tiền thanh toán
    private int total_bill(){
        return cus_Super_car.getPricesell()*quantityordercar;
    }
    
    public void showDetails(){
        myCustomer.showDetails();
        cus_Super_car.showDetails();
        System.out.println("Total bill: "+total_bill()+"\n");
    }
    public void showforCustomer(){
        myCustomer.showDetails();
        cus_Super_car.showforCustomer();
        System.out.print("total bill"+total_bill()+"\n");
    }
}
