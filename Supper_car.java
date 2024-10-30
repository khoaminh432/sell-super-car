public class Supper_car extends Car{
    
    private String CompanyCar;
    public static int QuantityCar = 0;
    public Supper_car(){
        super();
        CompanyCar = "none";
        QuantityCar++;
    }
    public Supper_car(int id_car,int pricebuy,int pricesell,float weight,float length,float height,float width,String Companycar){
        super(id_car,pricebuy,pricesell,weight,length,height,width);
        this.CompanyCar = Companycar;
        QuantityCar++;
    }
    public Supper_car(Supper_car sc){
        super(sc.getId_car(),sc.getPricebuy(),sc.getPricesell(),sc.getWeight(),sc.getLength(),sc.getHeight(),sc.getWidth());
        CompanyCar = sc.CompanyCar;
        QuantityCar++;
    }
    public String getCompanyCar(){
        return CompanyCar;
    }
    public void setCompanyCar(String CompanyCar){
        this.CompanyCar = CompanyCar;
    }
    
}
