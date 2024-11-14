package department_car;

import java.util.ArrayList;

public class List_luxury_car extends List_Car {

    public List_luxury_car() {
        super();
    }
    
    
    // tạo ds chỉ luxury car từ list super car
    public void setList(ArrayList<Super_car> sc){
        for(Super_car sCar:sc)
            if(Luxury_car.checkLuxuryCar(sCar))
                super.getList().add(sCar);
    }
    public void setlist(Luxury_car lc){
        super.add(lc);
    }
    public void setInteriorMaterial(int index,String interior_material){
        ((Luxury_car)super.getList().get(index)).setInteriorMaterial(interior_material);
    }
    public void setClimateControl(int index,String climate_control){
        ((Luxury_car)super.getList().get(index)).setClimateControl(climate_control);
    }
    public void setSoundSystem(int index,String sound_system){
        ((Luxury_car)super.getList().get(index)).setSoundSystem(sound_system);
    }
    public void setSafetyFeatures(int index,String safety_features){
        ((Luxury_car)super.getList().get(index)).setSafetyFeatures(safety_features);
    }


    
}