
import carstore.Customer;
import department_car.Super_car;
import java.util.Date;


public class bill {
    private int id_bill = 0;
    private Customer myCustomer;
    private Super_car cus_Super_car;
    private int quantityordercar;//số lượng xe được order
    private Date daytime;
    
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
    // chỉ người admin mới thay được
    public Customer getCustomer(){
        return myCustomer;
    }
    public Super_car getSuper_car(){
        return cus_Super_car;
    }
    //======================================
    // tổng số tiền thanh toán
    private int total_bill(){
        return cus_Super_car.getPricesell()*quantityordercar;
    }
    //cập nhật lại số xe của id xe đó(dùng khi người mua ấn nút mua thành công)
    public void quantityOrder(){
        cus_Super_car.setQuantityof_car(cus_Super_car.getQuantityof_car()-quantityordercar);
    }
    public void showDetails(){
        myCustomer.showDetails();
        cus_Super_car.showDetails();
        System.out.println("Total bill: "+total_bill()+"\n");
    }
    public void showforCustomer(){
        myCustomer.showDetails();
        cus_Super_car.showforCustomer();
        System.out.println("total bill"+total_bill()+"\n");
    }

}
