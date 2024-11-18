import department_car.*;

import java.util.Scanner;

import carstore.*;

public class main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuSystem menu = new MenuSystem(sc);
        menu.showLoginMenu();
        sc.close();
    }
}