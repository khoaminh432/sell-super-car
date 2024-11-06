public class Super_car extends Car{
    private String name;
    private String CompanyCar;
    
    public Super_car(){
        super();
        CompanyCar = "none";
    
    }
    public Super_car(int id_car,int pricebuy,int pricesell,float weight,float length,float height,float width,String Companycar){
        super(id_car,pricebuy,pricesell,weight,length,height,width);
        this.CompanyCar = Companycar;
        
    }
    public Super_car(Super_car sc){
        super(sc.getId_car(),sc.getPricebuy(),sc.getPricesell(),sc.getWeight(),sc.getLength(),sc.getHeight(),sc.getWidth());
        CompanyCar = sc.CompanyCar;
        
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getCompanyCar(){
        return CompanyCar;
    }
    public void setCompanyCar(String CompanyCar){
        this.CompanyCar = CompanyCar;
    }
    
}
