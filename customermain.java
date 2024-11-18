import carstore.CustomerManager;
import java.util.Scanner;
public class customermain {
    
    public static void main(String[] args) {
        CustomerManager cusmanager = new CustomerManager();
        cusmanager.readFromFile();
        cusmanager.customerManagementMenu(new Scanner(System.in));
    }
}