


public class Car {
    private int id_car=0;
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
        id_car ++;
        name = "";
        pricebuy = 0;
        pricesell = 0;
        weight = 0;
        length=  0;
        height=0;
        width = 0;
        quantityof_car = 0;
        QuantityCar++;
    }
    public Car(String name,int pricebuy,int pricesell,float weight,float length,float height,float width,int quantityof_car){
        id_car++;
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
    public Car(Car car){
        id_car++;
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
    
    public void setId_car(int id_car) {
        this.id_car = id_car;
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
    @Override
    public String toString() {
        return id_car+"\n"+name+"\n"+pricebuy+"\n"+pricesell+"\n"+weight+"\n"+length+
        "\n"+height+"\n"+width+"\n"+quantityof_car+"\n";
    }
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
    public void showforcustomer(){
        System.out.println("ID car: "+id_car);
        System.out.println("Name car:" +name);
        System.out.println("Price sell: " + pricesell);
        System.out.println("Weight: " + weight);
        System.out.println("Length: "+ length);
        System.out.println("height: " + height);
        System.out.println("width" + width);
    }
}
