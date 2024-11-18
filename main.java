import carstore.*;
import java.util.Scanner;

public class main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuSystem menu = new MenuSystem(sc);
        menu.showforCustomer();
        sc.close();
    }
}