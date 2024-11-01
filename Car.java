


public class Car {
    private int id_car;
    private int pricesell;
    private int pricebuy;
    private float weight;
    private float length;
    private float height;
    private float width;
    //khởi tạo
    public Car() {
        id_car = 0;
        pricebuy = 0;
        pricesell = 0;
        weight = 0;
        length=  0;
        height=0;
        width = 0;
    }
    public Car(int id_car,int pricebuy,int pricesell,float weight,float length,float height,float width){
        this.id_car = id_car;
        this.pricebuy = pricebuy;
        this.pricesell = pricesell;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
    }
    public Car(Car car){
        id_car = car.id_car;
        pricebuy = car.pricebuy;
        pricesell = car.pricesell;
        weight = car.weight;
        length = car.length;
        height = car.height;
        width = car.width;
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
    // hàm set
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

    
    @Override
    public String toString() {
        return id_car+"\n"+pricebuy+"\n"+pricesell+"\n"+weight+"\n"+length+"\n";
    }
    
}
