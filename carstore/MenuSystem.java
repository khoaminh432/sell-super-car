package carstore;
import java.util.Scanner;

public class MenuSystem {

    private Scanner sc = new Scanner(System.in);
    private CustomerManager customerManager = new CustomerManager();
    private StaffManager staffManager = new StaffManager();
    private Admin admin = new Admin();
    private BillManager billManager = new BillManager();

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
            int choice = sc.nextInt();
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
        System.out.println("Customer login successful.");
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
            System.out.println("Manager login successful.");
            showAdminMainMenu();
        }

        Staff staff = staffManager.login(email,password);
        if(staff == null){
            System.out.println("Email or password is incorrect.");
            System.out.println("Try again.");
            return;
        }

        System.out.println("Staff login successful.");
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
    
        System.out.println("Customer registered successfully.");
        return; // return to the login menu
    }
    
    //password recovery.
    //only available for customer or staff.
    //if admin forgot his password: i dont know.
    private void forgotPassword(){
        System.out.println("Enter your email: ");
        String email = ClientValidator.isEmailAdressValid(sc);

        if(admin.getEmail().equals(email)){
            System.out.println("You lost your account.");
            return;
        }

        Customer recoveryCustomer = customerManager.searchByEmailCaseSensitive(email);
        Staff recoveryStaff = staffManager.searchByEmailCaseSensitive(email);

        if(recoveryCustomer==null){
            if(recoveryStaff==null){
                System.out.println("This email is not registered.");
            }
            recoveryStaff.changePassword(sc);
            return;
        }
        recoveryCustomer.changePassword(sc);
    }

    private void showStaffMainMenu(Staff staff) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Staff Menu -----");
            System.out.println("1. View Profile");
            System.out.println("3. Manage Customer");
            System.out.println("4. Manage Car");
            System.out.println("5. Manage Bill");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice (1-6): ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    showStaffProfileMenu(staff);
                    break;
                case 3:
                    manageCustomerMenu();
                    break;
                case 4:
                    manageCarMenu();
                    break;
                case 5:
                    billManager.billManagerForStaff(sc);
                    break;
                case 6:
                    keepRunning = false;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }  

    private void manageCarMenu(){
        
    }

    private void showStaffProfileMenu(Staff staff) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Staff Profile Menu -----");
            System.out.println("1. View Profile Details");
            System.out.println("2. Update Information");
            System.out.println("3. Change password");
            System.out.println("4. Return to Previous Menu");
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
                    shoppingMenu(customer);
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


    private void showCustomerProfileMenu(Customer customer) {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("----- Profile Menu -----");
            System.out.println("1. View Profile Details");
            System.out.println("2. View Purchase History");
            System.out.println("3. Update Information");
            System.out.println("4. Change password");
            System.out.println("5. Delete account");
            System.out.println("6. Return to Previous Menu");
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
                    customer.changePassword(sc);
                    break;
                case 5:
                    System.out.println("WARNING! This action will permanently delete your account.");
                    System.out.print("Enter 1 to confirm (Press any other keys to exit): ");
                    String confirmation = sc.nextLine();
                    if ("1".equals(confirmation)) {
                        customerManager.delete(customer);
                        System.out.println("Account deleted successfully.");
                        System.out.println("Exiting system... Goodbye!");
                        System.exit(0);  // Terminate the program completely
                    } else {
                        System.out.println("Account deletion canceled.");
                    }
                    break;
                case 6:
                    keepRunning = false;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void shoppingMenu(Customer customer){

        System.out.println("---------------Shopping-------------");
        System.out.println("1. Show Luxury Car List");
        System.out.println("2. Show Sport Car List");
        System.out.println("3. Search for Luxury Car");
        System.out.println("4. Search for Sport Car");
        System.out.println("5. Return");
    }

    private void viewCartMenu(Customer customer){

    }
    
    private void showAdminMainMenu(){
        
    }
}
