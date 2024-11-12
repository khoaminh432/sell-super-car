package department_car;
import java.util.*;
public class List_luxury_car {
    private ArrayList<Luxury_car> car;
    
    public List_luxury_car()
    {
       car = new ArrayList<Luxury_car>();
    }
    //them
    public void addCar(Luxury_car lc)
    {
        car.add(lc);
    }
    public void addCar(int index, Luxury_car lc)
    {
        car.add(index, lc);
    }
    //xoa
    public boolean removeCar(Luxury_car lc)
    {
        return car.remove(lc);
    }
    // xuat list
    public void Output()
    {
        for(Luxury_car x : car)
        {
            System.out.println(x);
        }
    }
}