public class Luxury_car extends Super_car implements Ishowfor{
    private String interior_material; // vat lieu noi that
    private String sound_system; // he thong am thanh
    private String climate_control; // he thong may lanh
    private String safety_features; // chuc nang an toan
    public static int number_of_LuxuryCar = 0;
    public Luxury_car()
    {
        super();
        interior_material = "";
        sound_system = "";
        climate_control = "";
        safety_features = "";
        number_of_LuxuryCar++;
    }
    public Luxury_car(String name,int pricebuy,int pricesell,float weight,float length,float height,float width,int quantityof_car,String Companycar,String interior_material,String sound_system, String climate_control,String safety_features)
    {
        super(name,pricebuy,pricesell,weight,length,height,width,Companycar,quantityof_car);
        this.interior_material = interior_material;
        this.sound_system = sound_system;
        this.climate_control =  climate_control;
        this.safety_features = safety_features;
        number_of_LuxuryCar++;
    }
    public Luxury_car(Luxury_car lc)
    {
        super(lc.getName(),lc.getPricebuy(),lc.getPricesell(),lc.getWeight(),lc.getLength(),lc.getHeight(),lc.getWidth(),lc.getCompanyCar(),lc.getQuantityof_car());
        this.interior_material = lc.interior_material;
        this.sound_system = lc.sound_system;
        this.climate_control = lc.climate_control;
        this.safety_features = lc.safety_features;
    }
    //get,set interior_material
    public String getInteriorMaterial()
    {
        return interior_material;
    }
    public void setInteriorMaterial(String interior_material)
    {
        this.interior_material = interior_material;
    }
    //get,set sound_system
    public String getSoundSystem()
    {
        return sound_system;
    }
    public void setSoundSystem(String sound_system)
    {
        this.sound_system = sound_system;
    }
    //get,set climate_control
    public String getClimateControl()
    {
        return climate_control;
    }
    public void setClimateControl(String climate_control)
    {
        this.climate_control = climate_control;
    }
    //get,set safety_features
    public String getSafetyFeatures()
    {
        return safety_features;
    }
    public void setSafetyFeatures(String safety_features)
    {
        this.safety_features = safety_features;
    }
    

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Interior material"+interior_material);
        System.out.println("Sound system"+sound_system);
        System.out.println("Climate control"+climate_control);
        System.out.println("Safety features"+safety_features);
    }
    @Override
    public void showforCustomer(){
        super.showforCustomer();
        System.out.println("Interior material"+interior_material);
        System.out.println("Sound system"+sound_system);
        System.out.println("Climate control"+climate_control);
        System.out.println("Safety features"+safety_features);
    }
    
    
}
