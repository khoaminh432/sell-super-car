package department_car;

import java.util.ArrayList;
public class List_Car implements Ishowfor{
    private ArrayList<Super_car> Lcar;
    
    public List_Car(){
        Lcar = new ArrayList<>();
    }
    //thêm phần tử vào cuối mảng
    public void add(Super_car sc){
        Lcar.add(sc);
    }
    //thêm phần tử tại vị trí
    public void add(int index,Super_car sc){
        Lcar.add(index,sc);
    }
    // hàm xóa phàn tử tại vị trí index
    public void remove(int index){
        Lcar.remove(index);
    }
    public void output(){
        for (Super_car sc: Lcar)
            System.out.println(sc);
    }
    public ArrayList<Super_car> getList(){
        return Lcar;
    }
    public Super_car get(int index){
        return Lcar.get(index);
    }
    public int getindex(int id_car){
        for(int index = 0;index<Lcar.size();index++)
            if (Lcar.get(index).getId_car() == id_car)
                return index;
        return -1;
    }
    public int getindex(String name){
        for(int index=0;index<Lcar.size();index++)
            if(Lcar.get(index).getName().equals(name))
                return index;
        return -1;
    }
    //sửa 
    public void set(int index,Super_car sc){
        Lcar.set(index,sc);
    }
    public void setId_car(int index,int id_car){
        Lcar.set(index,Lcar.get(index)).setId_car(id_car);
    }
    public void setLcar(ArrayList<Super_car> lcar) {
        Lcar = lcar;
    }
    public void setHeight(int index,float height){
        Lcar.set(index,Lcar.get(index)).setHeight(height);
    }
    public void setLength(int index,float length){
        Lcar.set(index,Lcar.get(index)).setLength(length);
    }
    public void setWidth(int  index,float width){
        Lcar.set(index,Lcar.get(index)).setWidth(width);
    }
    public void setWeight(int  index,float weight){
        Lcar.set(index,Lcar.get(index)).setWeight(weight);
    }
    public void setPricebuy(int  index,int pricebuy){
        Lcar.set(index,Lcar.get(index)).setPricebuy(pricebuy);
    }
    public void setPricesell(int  index,int pricesell){
        Lcar.set(index,Lcar.get(index)).setPricesell(pricesell);
    }
    public void setCompanyCar(int  index,String CompanyCar){
        Lcar.set(index,Lcar.get(index)).setCompanyCar(CompanyCar);
        
    }

    // tìm kiếm
    public Super_car Search_idcar(int id_car){
        for(Super_car spc: Lcar)
            if (spc.getId_car() == id_car)
                return spc;
        return null;
    }
    
    public Super_car Search_name(String name){
        for(Super_car spc: Lcar)
            if (spc.getName().equals(name))
                return spc;
        return null;
    }

    @Override
    public void showDetails() {
        for(Super_car sc: Lcar){
            if(Luxury_car.checkLuxuryCar(sc))
                sc.showDetails();
            else if(Sport_car.checkSportCar(sc))
                sc.showDetails();
            else showDetails();
        }
    }

    @Override
    public void showforCustomer() {
        for(Super_car sc:Lcar)
        {   
            if(Luxury_car.checkLuxuryCar(sc))
                sc.showforCustomer();
            else if(Sport_car.checkSportCar(sc))
                sc.showforCustomer();
            else showforCustomer();
            }
    }
    
    
}
