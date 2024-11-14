package department_car;
import java.util.*;
public class Super_car extends Car implements Ishowfor{
    
    private String CompanyCar;
    public static int quantitysupper_car=0;
    public Super_car(){
        super();
        CompanyCar = "no";
        quantitysupper_car++;
    }
    public Super_car(String name,int pricebuy,int pricesell,float weight,float length,float height,float width,String Companycar,int quantityof_car){
        super(name,pricebuy,pricesell,weight,length,height,width,quantityof_car);
        this.CompanyCar = Companycar;
        quantitysupper_car++;
    }
    public Super_car(Super_car sc){
        super(sc.getName(),sc.getPricebuy(),sc.getPricesell(),sc.getWeight(),sc.getLength(),sc.getHeight(),sc.getWidth(),sc.getQuantityof_car());
        CompanyCar = sc.CompanyCar;
        quantitysupper_car++;
    }
    
    public String getCompanyCar(){
        return CompanyCar;
    }
    public void setCompanyCar(String CompanyCar){
        this.CompanyCar = CompanyCar;
    }
    public String toString(){
        return super.toString()+'\t'+CompanyCar;
    }
    @Override
    public void showDetails(){
        System.out.println("Company Car:" +CompanyCar);
        super.showDetails();
    }
    @Override
    public void showforCustomer(){
        System.out.println("Company Car:"+CompanyCar);
        super.showforCustomer();
    }
    public void input(){
        Scanner sc = new  Scanner(System.in);
        
        sc.close();
    }
    
    
}  
