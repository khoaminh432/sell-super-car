public class Sport_car extends Super_car{
    private String engine;
    private double top_speed;
    private double acceleration; //thời gian tăng tốc
    private String drive_type; //hệ thống dẫn động
    public static int number_of_sportCar=0;
    public Sport_car()
    {
        super();
        engine="";
        top_speed=0;
        acceleration=0;
        drive_type="";
    }
    public Sport_car(int id_car,int pricebuy,int pricesell,float weight,float length,float height,float width,String Companycar,String engine, double top_speed,double acceleration,String drive_type)
    {
        super(id_car,pricebuy,pricesell,weight,length,height,width,Companycar);
        this.engine = engine;
        this.top_speed = top_speed;
        this.acceleration = acceleration;
        this.drive_type = drive_type;
        number_of_sportCar++;
    }
    public Sport_car(Sport_car sc)
    {
        super(sc.getId_car(),sc.getPricebuy(),sc.getPricesell(),sc.getWeight(),sc.getLength(),sc.getHeight(),sc.getWidth(),sc.getCompanyCar());
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
    

}
