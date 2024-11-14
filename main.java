import department_car.List_Car;
import department_car.Luxury_car;
import department_car.Sport_car;
import department_car.Super_car;
public class main {
    public static void main(String[] args) {
        
        Super_car sc =new Super_car();
        Super_car sc1 = new Super_car();
        Luxury_car lc = new Luxury_car();
        Sport_car spc = new Sport_car();

        List_Car listca = new List_Car();
        listca.add(sc);
        listca.add(sc1);
        listca.add(lc);
        listca.add(spc);
        listca.remove(listca.getindex(1));
        listca.setCompanyCar(listca.getindex(2), "lamborgini");
        listca.writefile("list_file.txt");
        System.out.println(listca.getList().size());
        listca.showDetails();
    }
}