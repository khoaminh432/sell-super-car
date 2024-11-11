package department_car;

public class Super_car extends Car implements Ishowfor{
    
    private String CompanyCar;
    public static int quantitysupper_car;
    public Super_car(){
        super();
        CompanyCar = "none";
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
        return super.toString()+CompanyCar+'\n';
    
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

    
    
}  
