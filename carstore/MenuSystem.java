package carstore;
import department_car.List_Car;
import department_car.List_luxury_car;
import department_car.List_sport_car;
import department_car.Luxury_car;
import department_car.Sport_car;
import department_car.Super_car;
import java.util.Scanner;

public class MenuSystem {

    private Scanner sc ;
    private CustomerManager customerManager = new CustomerManager();
    private StaffManager staffManager = new StaffManager();
    private Admin admin = new Admin();
    private BillManager billManager = new BillManager();
    private List_Car lcCar = new List_Car();
    private List_luxury_car lxCar = new List_luxury_car();
    private List_sport_car lsCar = new List_sport_car();

    public MenuSystem(Scanner sc){
        this.sc = sc;
        customerManager.readFromFile();
        staffManager.readFromFile();
        billManager.readFromFile();
        lcCar.ReadFile("Data/list_car.txt");
    }

    /**
     * Displays the main login menu with options for customer and staff login,
     * customer registration, password recovery, and exiting the system.
     */
    public void showLoginMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Main Menu -----");
            System.out.println("1. Customer Login");
            System.out.println("2. Staff Login");
            System.out.println("3. Register");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice;
            try {choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("!!!Invalid amount. Please enter a valid number.!!!");
                continue;
            }
            sc.nextLine(); // Consume newline character
    
            switch (choice) {
                case 1:
                    customerLogin();
                    break;
                case 2:
                    staffLogin();
                    break;
                case 3:
                    registerCustomer();
                    break;
                case 4:
                    forgotPassword();
                    break;
                case 5:
                    keepRunning = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
    

    //Login as customer
    //show customer main menu if successful
    //if not, return to login menu
    private void customerLogin() {
        String email = ClientValidator.isEmailAdressValid(sc);
        String password = ClientValidator.isPasswordValid(sc);
        Customer customer = customerManager.login(email,password);

        if(customer==null){
            System.out.println("Email or password is incorrect.");
            System.out.println("Try again.");
            return;
        }
        System.out.println("***Customer login successful.***");
        showCustomerMainMenu(customer);
    }

    // Login as staff
    // Check if the admin is logging in first; if not, verify staff credentials.
    // Show admin menu if admin login is successful.
    // Show staff menu if staff login is successful.
    // If login fails, return to the login menu.
    private void staffLogin() {
        String email = ClientValidator.isEmailAdressValid(sc);
        String password = ClientValidator.isPasswordValid(sc);

        if(admin.login(email,password)){
            System.out.println("***Manager login successful.***");
            showAdminMainMenu();
        }

        Staff staff = staffManager.login(email,password);
        if(staff == null){
            System.out.println("Email or password is incorrect.");
            System.out.println("Try again.");
            return;
        }

        System.out.println("***Staff login successful.***");
        showStaffMainMenu(staff);
    }

    //Customer Register
    //call customerManager's add method
    private void registerCustomer() {
        System.out.println("----- Customer Registration -----");
        boolean registrationSuccess = customerManager.add();
        
        if (!registrationSuccess) {
            System.out.println("Failed to register. Please try again.");
            return; // return to the login menu 
        }
    
        System.out.println("***Customer registered successfully.***");
        return; // return to the login menu
    }
    
    //password recovery.
    //will send a recovery code to your email.
    //you need to enter the code to change your password.
    //DISCLAIMER: NO IT WONT, I DONT KNOW HOW TO SEND AN EMAIL BY JAVA CODE
    private void forgotPassword(){
        System.out.println("Enter your email: ");
        String email = ClientValidator.isEmailAdressValid(sc);

        if(admin.getEmail().equals(email)){
            System.out.println("Recovery code has been sent to your email.");
            System.out.println("......*Insert recovery code*)");
            admin.changePassword(sc);
            admin.saveData();
            return;
        }

        Customer recoveryCustomer = customerManager.searchByEmailCaseSensitive(email);
        Staff recoveryStaff = staffManager.searchByEmailCaseSensitive(email);

        if(recoveryCustomer==null){
            if(recoveryStaff==null){
                System.out.println("!!!This email is not registered.!!!");
            }
            System.out.println("Recovery code has been sent to your email.");
            System.out.println("......*Insert recovery code*)");
            recoveryStaff.changePassword(sc);
            customerManager.saveData();
            return;
        }
        System.out.println("Recovery code has been sent to your email.");
        System.out.println("......*Insert recovery code*)");
        recoveryCustomer.changePassword(sc);
        customerManager.saveData();
    }

    private void showStaffMainMenu(Staff staff) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Staff Menu -----");
            System.out.println("1. View Profile");
            System.out.println("2. Manage Customer");
            System.out.println("3. Manage Car");
            System.out.println("4. Manage Bill");
            System.out.println("5. Return to Login Menu");
            System.out.print("Enter your choice (1-5): ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    showStaffProfileMenu(staff);
                    break;
                case 2:
                    customerManager.customerManagementMenu(sc);
                    break;
                case 3:
                    lcCar.menuForManager(sc);
                    break;
                case 4:
                    billManager.billManagerForStaff(sc);
                    break;
                case 5:
                    keepRunning = false;
                    System.out.println("Returning to Login Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }  


    private void showStaffProfileMenu(Staff staff) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Staff Profile Menu -----");
            System.out.println("1. View Profile Details");
            System.out.println("2. Update Information");
            System.out.println("3. Change password");
            System.out.println("4. Return to Main Menu");
            System.out.print("Enter your choice (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    staff.showDetails();
                    break;
                case 2:
                    staffManager.update(staff);
                    break;
                case 3:
                    staff.changePassword(sc);
                    break;
                case 4:
                    keepRunning = false;
                    System.out.println("Returning to Staff Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


    private void showCustomerMainMenu(Customer customer) {
        boolean keepRunning = true;
        while (keepRunning) {
            customer.readPurchaseHistory(billManager);
            System.out.println("----- Customer Menu -----");
            System.out.println("1. View Profile");
            System.out.println("2. Buy Car");
            System.out.println("3. Return to login menu");
            System.out.print("Enter your choice (1-3): ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    showCustomerProfileMenu(customer);
                    break;
                case 2:
                    menuForCustomer(sc,customer);
                    break;
                case 3:
                    keepRunning = false;
                    System.out.println("Returning to Login Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


    public void showCustomerProfileMenu(Customer customer) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("-----Profile-----");
            System.out.println("1. View Profile Details");
            System.out.println("2. View Purchase History");
            System.out.println("3. Update Information");
            System.out.println("4. Change password");
            System.out.println("5. Delete account");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice (1-6): ");
            int choice = sc.nextInt();
            sc.nextLine(); 
    
            switch (choice) {
                case 1:
                    customer.showDetails();
                    break;
                case 2:
                    customer.showPurchaseHistory();
                    break;
                case 3:
                    customerManager.update(customer);
                    break;
                case 4:
                System.out.println("------Customer Password Recovery------");
                System.out.println("Create a new password: ");
                String newPassword = ClientValidator.isPasswordValid(sc);
                System.out.println("Confirm your password: ");
                String confirmPassword = ClientValidator.isPasswordValid(sc);
                if(newPassword==confirmPassword){
                   customerManager.getCustomer(customer).setPassword(newPassword);
                   System.out.println("***Password changed successfully.***");
                }
                    customerManager.writeToFile();
                    break;
                case 5:
                    System.out.println("WARNING!!! This action will permanently delete your account.***");
                    System.out.print("Enter 1 to confirm (Press any other keys to exit): ");
                    String confirmation = sc.nextLine();
                    if ("1".equals(confirmation)) {
                        customerManager.delete(customer);
                        System.out.println("Account deleted successfully.");
                        System.out.println("Exiting system... Goodbye!");
                        System.exit(0);  // Terminate the program completely
                    } else {
                        System.out.println("***Account deletion canceled.***");
                    }
                    break;
                case 6:
                    keepRunning = false;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("!!!Invalid choice. Please select a valid option.!!!");
            }
        }
        
    }


    
    private void showAdminMainMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Admin Menu -----");
            System.out.println("1. View Profile");
            System.out.println("2. Manage Customer");
            System.out.println("3. Manage Car");
            System.out.println("4. Manage Bill");
            System.out.println("5. Manage Staff");
            System.out.println("6. Return to Login Menu");
            System.out.print("Enter your choice (1-6): ");
    
            if (!sc.hasNextInt()) {
                sc.nextLine(); // Clear invalid input
                System.out.println("!!!Invalid input. Please enter a valid option.!!!");
                continue;
            }
    
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    admin.showDetails();
                    break;
                case 2:
                    customerManager.customerManagementMenu(sc);
                    break;
                case 3:
                    menuForManager(sc);
                    break;
                case 4:
                    billManager.billManagerForStaff(sc);
                    break;
                case 5:
                    staffManager.staffManagementMenu(sc);
                    break;
                case 6:
                    keepRunning = false;
                    System.out.println("Returning to Login Menu...");
                    break;
                default:
                    System.out.println("!!!Invalid choice. Please select a valid option.!!!");
            }
        }
    }

    private void buySuperCar(Customer customer, Super_car car){
        String carName = car.getName();
        int carId = car.getId_car();
        int stocks = car.getQuantityof_car();
        double price = car.getPricesell();
        String customerName = customer.getName();
        int customerId = customer.getID();
        System.out.println("You selected: " + carName + "\t ID: "+ carId);
        System.out.println("Stocks amount: " + stocks);

        int amount = - 1;
        while(true){
            System.out.println("Enter amount (enter 0 to exit): ");
            try {
                amount = Integer.parseInt(sc.nextLine().trim());
                if (amount < 0){
                    System.out.println("!!!Amount must be more than 0!!!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("!!!Invalid amount. Please enter a valid number.!!!");
                continue;
            }
            if(amount > stocks){
                System.out.println("Amount must be not more than " + stocks);
                continue;
            }
            break;
        }
        System.out.println("Choose payment method: ");
        System.out.println("1. By Cash");
        System.out.println("2. By Debit Card");
        System.out.println("3. By Credit Card");
        System.out.println("Press any to exit");
        int choice;
        try {choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("===Pay by cash. Please show the bill to the cashier===");
                break;
            case 2:
                System.out.println("===Pay by Debit Card. Please show the bill to the cashier===");
                break;
            case 3:
                System.out.println("===Pay by Credit Card. Please show the bill to the cashier===");
                break;
            default:
                return;
        }
        car.setQuantityof_car(stocks-amount);
        Bill newBill = new Bill(carName, carId, amount, price, customerName, customerId);
        newBill.showDetails();
        billManager.createNewBill(newBill);
        System.out.println("***Buy car successfully.***");
        billManager.writeToFile();
    }

    private void buyLuxuryCar(Customer customer, Super_car car){
        String carName = car.getName();
        int carId = car.getId_car();
        int stocks = car.getQuantityof_car();
        double price = car.getPricesell();
        String customerName = customer.getName();
        int customerId = customer.getID();
        System.out.println("You selected: " + carName + "\t ID: "+ carId);
        System.out.println("Stocks amount: " + stocks);

        int amount = - 1;
        while(true){
            System.out.println("Enter amount (enter 0 to exit): ");
            try {
                amount = Integer.parseInt(sc.nextLine().trim());
                if (amount < 0){
                    System.out.println("!!!Amount must be more than 0!!!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("!!!Invalid amount. Please enter a valid number.!!!");
                continue;
            }
            if(amount > stocks){
                System.out.println("Amount must be not more than " + stocks);
            }
            break;
        }
        System.out.println("Choose payment method: ");
        System.out.println("1. By Cash");
        System.out.println("2. By Debit Card");
        System.out.println("3. By Credit Card");
        System.out.println("Press any to exit");
        int choice;
        try {choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("===Paid by cash. Please show the bill to the cashier===");
                break;
            case 2:
                System.out.println("===Paid by Debit Card. Please show the bill to the cashier===");
                break;
            case 3:
                System.out.println("===Paid by Credit Card. Please show the bill to the cashier===");
                break;
            default:
                return;
        }
        car.setQuantityof_car(stocks-amount);
        Bill newBill = new Bill(carName, carId, amount, price, customerName, customerId);
        newBill.showDetails();
        billManager.createNewBill(newBill);
        System.out.println("***Buy car successfully.***");
        billManager.writeToFile();
    }

    private void buySportCar(Customer customer, Sport_car car){
        String carName = car.getName();
        int carId = car.getId_car();
        int stocks = car.getQuantityof_car();
        double price = car.getPricesell();
        String customerName = customer.getName();
        int customerId = customer.getID();
        System.out.println("You selected: " + carName + "\t ID: "+ carId);
        System.out.println("Stocks amount: " + stocks);

        int amount = - 1;
        while(true){
            System.out.println("Enter amount (enter 0 to exit): ");
            try {
                amount = Integer.parseInt(sc.nextLine().trim());
                if (amount < 0){
                    System.out.println("!!!Amount must be more than 0!!!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("!!!Invalid amount. Please enter a valid number.!!!");
                continue;
            }
            if(amount > stocks){
                System.out.println("Amount must be not more than " + stocks);
                continue;
            }
            break;
        }
        System.out.println("Choose payment method: ");
        System.out.println("1. By Cash");
        System.out.println("2. By Debit Card");
        System.out.println("3. By Credit Card");
        System.out.println("Press any to exit");
        int choice;
        try {choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("Pay by cash. Please show the bill to the cashier");
                break;
            case 2:
                System.out.println("Pay by Debit Card. Please show the bill to the cashier");
                break;
            case 3:
                System.out.println("Pay by Credit Card. Please show the bill to the cashier");
                break;
            default:
                return;
        }
        car.setQuantityof_car(stocks-amount);
        Bill newBill = new Bill(carName, carId, amount, price, customerName, customerId);
        newBill.showDetails();
        billManager.createNewBill(newBill);
        System.out.println("***Buy car successfully.***");
        billManager.writeToFile();
    }

    public void menuForCustomer(Scanner sc, Customer customer) {
        boolean keepRunning = true;
        
        while (keepRunning) {
            System.out.println("=========== CUSTOMER MENU ===========");
            System.out.println("1. Buy Super Car");
            System.out.println("2. Buy Luxury Car");
            System.out.println("3. Buy Sport Car");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue; // Skip the rest of the loop and show the menu again
            }
    
            switch (choice) {
                case 1:
                    Super_car superCar = lcCar.search(sc);  
                    if (superCar != null) {
                        buySuperCar(customer,superCar);
                    }
                    break;
                    
                case 2:
                    Luxury_car luxuryCar = lxCar.searchLXCar(sc);  
                    if (luxuryCar != null) {
                        System.out.println("You selected: " + luxuryCar.getName());
                        System.out.print("Do you want to buy this car? (y/n): ");
                        String luxuryChoice = sc.nextLine().trim().toLowerCase();
                        if (luxuryChoice.equals("y")) {
                            buyLuxuryCar(customer,luxuryCar); // Assuming the customer class can handle this
                            System.out.println("Thank you for buying the car!");
                        }
                    }
                    break;
    
                case 3:
                    // Buy Sport Car (Similar to Super Car)
                    Sport_car sportCar = lsCar.searchSPCar(sc);  // Assuming a separate search for sport cars
                    if (sportCar != null) {
                        System.out.print("Do you want to buy this car? (y/n): ");
                        String sportChoice = sc.nextLine().trim().toLowerCase();
                        if (sportChoice.equals("y")) {
                            buySportCar(customer,sportCar); // Assuming the customer class can handle this
                            System.out.println("***Thank you for buying the car!***");
                        }
                    }
                    break;
    
                case 0:
                    keepRunning = false;  // Exit the menu loop
                    System.out.println("***Thank you for visiting our car store!***");
                    break;
    
                default:
                    System.out.println("!!!!Invalid choice. Please enter a valid option (1-3).!!!!");
            }
        }
    }

    public void menuForManager(Scanner sc){
        boolean keepRunning = true;
        
        while (keepRunning) {
            System.out.println("=========== CUSTOMER MENU ===========");
            System.out.println("1. Manage Super Car");
            System.out.println("2. Manage Luxury Car");
            System.out.println("3. Manage Sport Car");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue; // Skip the rest of the loop and show the menu again
            }
    
            switch (choice) {
                case 1:
                    lcCar.menuForManager(sc);
                    break;
                    
                case 2:
                    lxCar.menuForManager(sc);
                    break;
    
                case 3:
                    lsCar.menuForManager(sc);
                    break;
    
                case 0:
                    keepRunning = false;  // Exit the menu loop
                    System.out.println("Thank you for visiting our car store!");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-3).");
            }
        }
    }
}
