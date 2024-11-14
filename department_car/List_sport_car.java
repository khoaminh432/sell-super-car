package department_car;
import java.util.ArrayList;
public class List_sport_car extends List_Car {

    public List_sport_car() {
        super();
    }

    
    
    // tạo danh sách chỉ sport car từ danh sách super car
    public void setList(ArrayList<Super_car> sc) {
        for (Super_car sCar : sc) {
            if (Sport_car.checkSportCar(sCar)) {
                super.getList().add(sCar);
            }
        }
    }

    public void setlist(Sport_car sp) {
        super.add(sp);
    }

    public void setTopSpeed(int index, double top_speed) {
        ((Sport_car) super.getList().get(index)).setTopSpeed(top_speed);
    }

    public void setAcceleration(int index, double acceleration) {
        ((Sport_car) super.getList().get(index)).setAcceleration(acceleration);
    }

    public void setEngine(int index, String engine) {
        ((Sport_car) super.getList().get(index)).setEngine(engine);
    }

    public void setDriveType(int index, String drive_type) {
        ((Sport_car) super.getList().get(index)).setDriveType(drive_type);
    }

   
}
