
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
    //sửa 
    public void set(int index,Supper_car sc){
        Lcar.set(index,sc);
    }
    public void setId_car(int index,int id_car){
        Lcar.set(index,Lcar.get(index)).setId_car(id_car);
    }
    public void setLcar(ArrayList<Supper_car> lcar) {
        Lcar = lcar;
    }
    public void setHeight(int  index,float height){
        Lcar.set(index,Lcar.get(index)).setHeight(height);
    }
    public void setLength(int  index,float length){
        Lcar.set(index,Lcar.get(index)).setHeight(length);
    }
    public void setWidth(int  index,float width){
        Lcar.set(index,Lcar.get(index)).setHeight(width);
    }
    public void setWeight(int  index,float weight){
        Lcar.set(index,Lcar.get(index)).setHeight(weight);
    }
    public void setPricebuy(int  index,int pricebuy){
        Lcar.set(index,Lcar.get(index)).setHeight(pricebuy);
    }
    public void setPricesell(int  index,float pricesell){
        Lcar.set(index,Lcar.get(index)).setHeight(pricesell);
    }
    public void setCompanyCar(int  index,float CompanyCar){
        Lcar.set(index,Lcar.get(index)).setHeight(CompanyCar);
    }
    
}
