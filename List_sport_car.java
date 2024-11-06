import java.util.*;
public class List_sport_car {
    private ArrayList<Sport_car> car;
    
    public List_sport_car()
    {
       car = new ArrayList<Sport_car>();
    }
    //them
    public void addCar(Sport_car sc)
    {
        car.add(sc);
    }
    public void addCar(int index, Sport_car sc)
    {
        car.add(index, sc);
    }
    //xoa
    public boolean removeCar(Sport_car sc)
    {
        return car.remove(sc);
    }
    // xuat list
    public void Output()
    {
        for(Sport_car x : car)
        {
            System.out.println(x);
        }
    }

}
