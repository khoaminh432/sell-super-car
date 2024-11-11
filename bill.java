
import carstore.Customer;


public class bill {
    private int id_bill = 0;
    private Customer myCustomer;
    private Super_car cus_Super_car;
    private int quantitycar;
    public static int quantitybill = 0;

    public bill(){
        id_bill++;
        quantitybill++;
        quantitycar = 0;
    }
    public bill(Customer myCustomer,Super_car cSuper_car){
        id_bill++;
        quantitybill++;
        this.myCustomer = myCustomer;
        cus_Super_car = cSuper_car;
    }
    public int getId_bill() {
        return id_bill;
    }
    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }
    // tổng số tiền thanh toán
    public int total_bill(int quantitycar){
        cus_Super_car.setQuantityof_car(cus_Super_car.getQuantityof_car()-quantitycar);
        this.quantitycar = quantitycar;
        return cus_Super_car.getPricesell()*quantitycar;
    }
    public void showDetails(){
        myCustomer.showDetails();
        
    }
}
