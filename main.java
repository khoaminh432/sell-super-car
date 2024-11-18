import department_car.*;

import java.util.Scanner;

import carstore.*;

public class main{
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.showDetails();
        MenuSystem menu = new MenuSystem();
        menu.readFiles();
        menu.showLoginMenu();
    }
}