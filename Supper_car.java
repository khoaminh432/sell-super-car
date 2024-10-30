public class Supper_car extends Car{
    private String CompanyCar;
    public static int QuantityCar = 0;
    public Supper_car(){
        CompanyCar = "none";
    }
    public String getCompanyCar(){
        return CompanyCar;
    }
    public void setCompanyCar(String CompanyCar){
        this.CompanyCar = CompanyCar;
    }
}
