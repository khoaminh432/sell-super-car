<<<<<<< Updated upstream
import carstore.CustomerManager;
import java.util.Scanner;
public class customermain {
    
    public static void main(String[] args) {
        CustomerManager cusmanager = new CustomerManager();
        cusmanager.readFromFile();
        cusmanager.customerManagementMenu(new Scanner(System.in));
=======
import java.util.Scanner;

import department_car.List_Car;
public class customermain {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        List_Car car = new List_Car();
        car.menuForCustomer(sc);

>>>>>>> Stashed changes
    }
}