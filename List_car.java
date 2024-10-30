
import java.util.ArrayList;
public class List_Car{
    private ArrayList<Supper_car> Lcar;
    
    public List_Car(){
        Lcar = new ArrayList<>();
        
    }
    //thêm phần tử vào cuối mảng
    public void add(Supper_car sc){
        Lcar.add(sc);
    }
    //thêm phần tử tại vị trí
    public void add(int index,Supper_car sc){
        Lcar.add(index,sc);
    }
    // hàm xóa phàn tử tại vị trí index
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
    
    public void set(int index,Supper_car sc){
        Lcar.set(index,sc);
    }
    
}
