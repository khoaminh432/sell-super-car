package department_car;
import java.util.ArrayList;
import java.util.Scanner;
public class Car implements Ishowfor{
    private int id_car;
    private String name;
    private int pricesell;
    private int pricebuy;
    private float weight;
    private float length;
    private float height;
    private float width;
    private int quantityof_car;
    public static int QuantityCar = 0;
    
    //khởi tạo
    public Car() {
        id_car = 0;
        name = "none";
        pricebuy = 0;
        pricesell = 0;
        weight = 0;
        length=  0;
        height=0;
        width = 0;
        quantityof_car = 0;
        QuantityCar++;
        
    }
    public Car(int id_car,String name,int pricebuy,int pricesell,float weight,float length,float height,float width,int quantityof_car){
        this.id_car = id_car;
        this.name = name;
        this.pricebuy = pricebuy;
        this.pricesell = pricesell;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.quantityof_car = quantityof_car;
        QuantityCar++;
        
    }
    public Car(ArrayList<String> car_infor){
        id_car = chooseInteger(car_infor.get(0));
        name = car_infor.get(1);
        pricebuy = chooseInteger(car_infor.get(2));
        pricesell = chooseInteger(car_infor.get(3));
        weight = chooseFloat(car_infor.get(4));
        length = chooseFloat(car_infor.get(5));
        height = chooseFloat(car_infor.get(6));
        width = chooseFloat(car_infor.get(7));
        quantityof_car = chooseInteger(car_infor.get(8));
        

    }
    public Car(Car car){
        id_car = car.id_car;
        name = car.name;
        pricebuy = car.pricebuy;
        pricesell = car.pricesell;
        weight = car.weight;
        length = car.length;
        height = car.height;
        width = car.width;
        quantityof_car = car.quantityof_car;
        QuantityCar++;
        
    }
    // hàm get

    public int getId_car() {
        return id_car;
    }
    public int getPricesell(){
        return pricesell;
    }
    public int getPricebuy() {
        return pricebuy;
    }
    public float getWeight() {
        return weight;
    }
    public float getLength() {
        return length;
    }
    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
    }
    
    public String getName() {
        return name;
    }
    public int getQuantityof_car() {
        return quantityof_car;
    }
    // hàm set
    public void setId_car(int id_car) {
        this.id_car = id_car;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setPricebuy(int pricebuy) {
        this.pricebuy = pricebuy;
    }
    public void setPricesell(int pricesell) {
        this.pricesell = pricesell;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public void setQuantityof_car(int quantityof_car) {
        this.quantityof_car = quantityof_car;
    }
    public static boolean RealNumber(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean IntegerNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean DoubleNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    public static int chooseInteger(String str){
        int m;
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println(str+" must be an Integer!");
            return -1;
        }}

    public static float chooseFloat(String str){
        float m;
    try{
            return Float.parseFloat(str);
            
        }catch (NumberFormatException e){
            System.out.println(str+" must be an Float!");
            return  -1;
        }}
    public static double  chooseDouble(String str){
        double m;
        
            try{
                return Double.parseDouble(str);
                
            }
            catch(NumberFormatException e){
                System.out.println(str+"must be an Double!");
                return  -1;
            }
        } 
    @Override
    public String toString() {
        return id_car+"\t"+name+"\t"+pricebuy+"\t"+pricesell+"\t"+weight+"\t"+length+
        "\t"+height+"\t"+width+"\t"+quantityof_car;
    }
    @Override
    public void showDetails(){
        System.out.println("ID car: "+id_car);
        System.out.println("Name car:" +name);
        System.out.println("Price buy: " + pricebuy);
        System.out.println("Price sell: " + pricesell);
        System.out.println("Weight: " + weight);
        System.out.println("Length: "+ length);
        System.out.println("height: " + height);
        System.out.println("width" + width);
        System.out.println("quantity car: " + quantityof_car);
    }
    @Override
    public void showforCustomer(){
        System.out.println("ID car: "+id_car);
        System.out.println("Name car:" +name);
        System.out.println("Price sell: " + pricesell);
        System.out.println("Weight: " + weight);
        System.out.println("Length: "+ length);
        System.out.println("height: " + height);
        System.out.println("width: " + width);
    }
    public void input(){
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        pricebuy = sc.nextInt();
        pricesell = sc.nextInt();
        weight = sc.nextFloat();
        length = sc.nextFloat();
        height = sc.nextFloat();
        width = sc.nextFloat();
        quantityof_car = sc.nextInt();
        sc.close();
    }

}   
