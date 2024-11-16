import department_car.List_Car;
public class customermain {
    private void menu(){
        System.out.println("===================Welcome customer====================");
        System.out.println("1. Display vehicle list.");
        System.out.println("2. Show your profile.");
        System.out.println("3. ");
        System.out.println("0. exit.");
        System.out.print("Your choose: ");

    }
    public static void main(String[] args) {
        
        
        List_Car listca = new List_Car();
        
        listca.ReadFile("list_car.txt");

    }
}