package department_car;

import java.util.ArrayList;

public class Sport_car extends Super_car implements Ishowfor{
    private String engine;
    private double top_speed;
    private double acceleration; //thời gian tăng tốc
    private String drive_type; //hệ thống dẫn động
    public static int number_of_sportCar=0;
    public Sport_car()
    {
        super();
        engine="none";
        top_speed=0;
        acceleration=0;
        drive_type="none";
        number_of_sportCar++;
    }
    public Sport_car(ArrayList<String> sportcar){
        super(new ArrayList<>(sportcar.subList(0, 10)));
        engine=sportcar.get(10);
        top_speed=chooseDouble(sportcar.get(11));
        acceleration=chooseDouble(sportcar.get(12));
        drive_type=sportcar.get(13);
        number_of_sportCar++;
    }
    public Sport_car(int id_car,String name,int pricebuy,int pricesell,float weight,float length,float height,float width,int quantityof_car,String Companycar,String engine, double top_speed,double acceleration,String drive_type)
    {
        super(id_car,name,pricebuy,pricesell,weight,length,height,width,quantityof_car,Companycar);
        this.engine = engine;
        this.top_speed = top_speed;
        this.acceleration = acceleration;
        this.drive_type = drive_type;
        number_of_sportCar++;
    }
    public Sport_car(Sport_car sc)
    {
        super(sc.getId_car(),sc.getName(),sc.getPricebuy(),sc.getPricesell(),sc.getWeight(),sc.getLength(),sc.getHeight(),sc.getWidth(),sc.getQuantityof_car(),sc.getCompanyCar());
        this.engine = sc.engine;
        this.top_speed = sc.top_speed;
        this.acceleration = sc.acceleration;
        this.drive_type = sc.drive_type;
        number_of_sportCar++;
    }
    
    //get,set engine
    public String getEngine()
    {
        return engine;
    }
    public void setEngine(String engine)
    {
        this.engine = engine;
    }
    //get,set speed
    public double getTopSpeed()
    {
        return top_speed;
    }
    public void setTopSpeed(double top_speed)
    {
        this.top_speed = top_speed;
    }
    //get,set acceleration
    public double getAcceleration()
    {
        return acceleration;
    }
    public void setAcceleration(double acceleration)
    {
        this.acceleration = acceleration;
    }
    //get,sẻt drive_type
    public String getDriveType()
    {
        return drive_type;
    }
    public void setDriveType(String drive_type)
    {
        this.drive_type = drive_type;
    }
    @Override
    public void showDetails() {
        // TODO Auto-generated method stub
        super.showDetails();
        System.out.println("Engine:" +engine);
        System.out.println("Top speed: "+top_speed);
        System.out.println("Acceleration: "+acceleration);
        System.out.println("Drive type: "+drive_type);
        
    }
    public static boolean checkSportCar(Super_car sc){
        return sc instanceof Sport_car;
    }
    @Override
    public void showforCustomer(){
        super.showforCustomer();
        System.out.println("Engine:" +engine);
        System.out.println("Top speed: "+top_speed);
        System.out.println("Acceleration: "+acceleration);
        System.out.println("Drive type: "+drive_type);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString()+'\t'+engine+'\t'+top_speed+'\t'+acceleration+'\t'+drive_type;
    }
    public static void quantitycar(){
        System.out.println("Quantity Sport Car: "+number_of_sportCar);
    }

}
