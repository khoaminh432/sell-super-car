
import java.util.ArrayList;
public class List_Car{
    ArrayList<Supper_car> Lcar;
    public List_Car(){
        Lcar = new ArrayList<>();
    }
    public void add(Supper_car sc){
        Lcar.add(sc);
    }
    public void add(int index,Supper_car sc){
        Lcar.add(index,sc);
    }
    public void remove(int index){
        Lcar.remove(index);
    }
    public void output(){
        for (Supper_car sc: Lcar)
            System.out.println(sc);
    }
    public Supper_car get(int index){
        return Lcar.get(index);
    }
    //set
    public void set(int index,Supper_car sc){
        Lcar.set(index,sc);
    }
    
}
