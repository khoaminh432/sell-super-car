
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
    //set1
    public void setCompanyCar(int index,String companycar){
        Lcar.set(index,Lcar.get(index)).setCompanyCar(companycar);
    }
    //set2
    public void setId_Car(int index,int id_Car){
        Lcar.set(index,Lcar.get(index)).setId_car(id_Car);
    }
    //set3
    public void setPricebuy(int index,int pricebuy){
        Lcar.set(index,Lcar.get(index)).setPricebuy(pricebuy);
    }
    //set4
    public void setpricesell(int index,int pricesell){
        Lcar.set(index,Lcar.get(index)).setPricesell(pricesell);
    }
    //set5
    public void setWeight(int index,float weight){
        Lcar.set(index,Lcar.get(index)).setWeight(weight);
    }
    //set6
    public void setlength(int index,float length){
        Lcar.set(index,Lcar.get(index)).setLength(length);
    }
}
