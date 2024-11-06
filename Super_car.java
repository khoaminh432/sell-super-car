public class Super_car extends Car{
    
    private String CompanyCar;
    public static int quantitysupper_car;
    public Super_car(){
        super();
        CompanyCar = "none";
        quantitysupper_car++;
    }
    public Super_car(int pricebuy,int pricesell,float weight,float length,float height,float width,String Companycar){
        super(pricebuy,pricesell,weight,length,height,width);
        this.CompanyCar = Companycar;
        quantitysupper_car++;
    }
    public Super_car(Super_car sc){
        super(sc.getPricebuy(),sc.getPricesell(),sc.getWeight(),sc.getLength(),sc.getHeight(),sc.getWidth());
        CompanyCar = sc.CompanyCar;
        quantitysupper_car++;
    }
    
    public String getCompanyCar(){
        return CompanyCar;
    }
    public void setCompanyCar(String CompanyCar){
        this.CompanyCar = CompanyCar;
    }
    
}
