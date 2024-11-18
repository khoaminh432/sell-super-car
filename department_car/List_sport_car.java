package department_car;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import carstore.IdManager;
public class List_sport_car extends List_Car {
    private ArrayList<Sport_car> Lscar;
    private IdManager id_car = new IdManager(ID_FILE_NAME);
    private static final String SPORTCAR_FILE_NAME = "Data/sportCar.txt";
    private static final String ID_FILE_NAME = "Data/sportCarID.txt";
    public List_sport_car() {
        super();
        Lscar = new ArrayList<>();
    }

    
    
    // tạo danh sách chỉ sport car từ danh sách super car
    public void setList(ArrayList<Super_car> sc) {
        for (Super_car sCar : sc) {
            if (Sport_car.checkSportCar(sCar)) {
                super.getList().add(sCar);
            }
        }
    }
    @Override
    public void add(Scanner scanner){
        System.out.println("=================ADD=============");
        int id = id_car.idGenerator();
        System.out.println("Name: ");String name = scanner.nextLine().trim();
        System.out.println("Pricesell: ");int pricesell = scanner.nextInt();
        System.out.println("Pricebuy: ");int pricebuy = scanner.nextInt();
        System.out.println("Weight: ");float weight = scanner.nextFloat();
        System.out.println("Length: ");float length = scanner.nextFloat();
        System.out.println("Height: ");float height = scanner.nextFloat();
        System.out.println("Width: ");float width = scanner.nextFloat();
        System.out.println("The number of cars: ");int quantityof_car = scanner.nextInt();
        System.out.println("Company: ");String CompanyCar = scanner.nextLine().trim();
        System.out.println("Engine:  ");String engine = scanner.nextLine().trim();
        System.out.println("Top speed: ");Double top_speed = scanner.nextDouble();
        System.out.println("Acceleration: ");double acceleration = scanner.nextDouble();
        System.out.println("Drive Type: ");String drive = scanner.nextLine().trim();
        Sport_car newCar = new Sport_car(id,name,pricebuy,pricesell,weight,length,height,width,quantityof_car,CompanyCar,engine,top_speed,acceleration,drive);
        Lscar.add(newCar);
    }
    

    
    public void set(int index,Sport_car sc){
        Lscar.set(index,sc);
    }
    @Override
    public void WriteFile(String filename){
        try{
            FileWriter fw = new FileWriter(filename);
            for(Sport_car lc: Lscar){
                fw.write(lc.toString()+"\n");
            }
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setTopSpeed(int index, double top_speed) {
        ((Sport_car) super.getList().get(index)).setTopSpeed(top_speed);
    }

    public void setAcceleration(int index, double acceleration) {
        ((Sport_car) super.getList().get(index)).setAcceleration(acceleration);
    }

    public void setEngine(int index, String engine) {
        ((Sport_car) super.getList().get(index)).setEngine(engine);
    }

    public void setDriveType(int index, String drive_type) {
        ((Sport_car) super.getList().get(index)).setDriveType(drive_type);
    }

    @Override
    public void menuForManager(Scanner scanner) {
        int choice;
        do {
            System.out.println("====================MENU===============");
            System.out.println("1. Add a new super car");
            System.out.println("2. Remove car");
            System.out.println("3. Change the information of car");
            System.out.println("4. Search car");
            System.out.println("5. Show all cars");
            System.out.println("6. Save");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    add(scanner);
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 2:
                Sport_car carToRemove = searchSPCar(scanner);
                    if (carToRemove != null) {
                        Super_car super_carToRemove = (Super_car) carToRemove;
                        Remove(super_carToRemove);
                    }
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 3:
                    Sport_car carToUpdate = searchSPCar(scanner);
                    if (carToUpdate != null) {
                        carToUpdate.showDetails();
                        update(scanner, carToUpdate);
                    }
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 4:
                    Sport_car carToSearch = searchSPCar(scanner);
                    if (carToSearch != null) {
                        carToSearch.showDetails();
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
    
                case 5:
                    System.out.println("=============LIST CAR==================");
                    for (Sport_car car : Lscar) {
                        car.showDetails();
                    }
                    break;
    
                case 6:
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    
        } while (choice != 0);
    }
    
    private void update(Scanner scanner, Sport_car sc) {
        int choose = -1;
        do {
            System.out.println("===============CHANGE OPTION============");
            System.out.println("1. Change All");
            System.out.println("2. Change Engine");
            System.out.println("3. Change Top Speed");
            System.out.println("4. Change Acceleration");
            System.out.println("5. Change Drive Type");
            System.out.println("6. Change Pricesell");
            System.out.println("7. Change Pricebuy");
            System.out.println("8. Change Length");
            System.out.println("9. Change Weight");
            System.out.println("10. Change Height");
            System.out.println("11. Change Width");
            System.out.println("0. Exit");
            System.out.println("Enter your choice (1-11):");
            choose = scanner.nextInt();
            scanner.nextLine();  // Consume the newline left over by nextInt()
    
            switch (choose) {
                case 1:
                    // Update all attributes, including Sport_car-specific attributes
                    System.out.println("Enter new Name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.println("Enter new Price Sell: ");
                    int newPricesell = scanner.nextInt();
                    System.out.println("Enter new Price Buy: ");
                    int newPricebuy = scanner.nextInt();
                    System.out.println("Enter new Length: ");
                    float newLength = scanner.nextFloat();
                    System.out.println("Enter new Height: ");
                    float newHeight = scanner.nextFloat();
                    System.out.println("Enter new Weight: ");
                    float newWeight = scanner.nextFloat();
                    System.out.println("Enter new Width: ");
                    float newWidth = scanner.nextFloat();
                    System.out.println("Enter new Company: ");
                    scanner.nextLine();  // consume newline
                    String newCompany = scanner.nextLine().trim();
                    System.out.println("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();  // consume newline
    
                    // Update the Super_car with new values
                    sc.setName(newName);
                    sc.setPricesell(newPricesell);
                    sc.setPricebuy(newPricebuy);
                    sc.setLength(newLength);
                    sc.setHeight(newHeight);
                    sc.setWeight(newWeight);
                    sc.setWidth(newWidth);
                    sc.setCompanyCar(newCompany);
                    sc.setQuantityof_car(newQuantity);
    
                    // Update Sport_car-specific attributes
                    System.out.println("Enter new Engine: ");
                    String newEngine = scanner.nextLine().trim();
                    System.out.println("Enter new Top Speed: ");
                    double newTopSpeed = scanner.nextDouble();
                    System.out.println("Enter new Acceleration (time in seconds): ");
                    double newAcceleration = scanner.nextDouble();
                    scanner.nextLine();  // consume newline
                    System.out.println("Enter new Drive Type: ");
                    String newDriveType = scanner.nextLine().trim();
    
                    sc.setEngine(newEngine);
                    sc.setTopSpeed(newTopSpeed);
                    sc.setAcceleration(newAcceleration);
                    sc.setDriveType(newDriveType);
                    break;
    
                case 2:
                    System.out.println("Enter new Engine: ");
                    String engine = scanner.nextLine().trim();
                    sc.setEngine(engine);
                    break;
    
                case 3:
                    System.out.println("Enter new Top Speed: ");
                    double topSpeed = scanner.nextDouble();
                    sc.setTopSpeed(topSpeed);
                    break;
    
                case 4:
                    System.out.println("Enter new Acceleration (time in seconds): ");
                    double acceleration = scanner.nextDouble();
                    sc.setAcceleration(acceleration);
                    break;
    
                case 5:
                    System.out.println("Enter new Drive Type: ");
                    String driveType = scanner.nextLine().trim();
                    sc.setDriveType(driveType);
                    break;
    
                case 6:
                    System.out.println("Enter new Price Sell: ");
                    int priceSell = scanner.nextInt();
                    sc.setPricesell(priceSell);
                    break;
    
                case 7:
                    System.out.println("Enter new Price Buy: ");
                    int priceBuy = scanner.nextInt();
                    sc.setPricebuy(priceBuy);
                    break;
    
                case 8:
                    System.out.println("Enter new Length: ");
                    float length = scanner.nextFloat();
                    sc.setLength(length);
                    break;
    
                case 9:
                    System.out.println("Enter new Weight: ");
                    float weight = scanner.nextFloat();
                    sc.setWeight(weight);
                    break;
    
                case 10:
                    System.out.println("Enter new Height: ");
                    float height = scanner.nextFloat();
                    sc.setHeight(height);
                    break;
    
                case 11:
                    System.out.println("Enter new Width: ");
                    float width = scanner.nextFloat();
                    sc.setWidth(width);
                    break;
    
                case 0:
                    System.out.println("Exiting update menu...");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choose != 0);
    }

    public Sport_car searchSPCar(Scanner sc) {
        int choice = -1;
        
        // Prompt user for search type with validation
        while (choice != 0) {
            System.out.println("1. Search by Id.");
            System.out.println("2. Search by name.");
            System.out.println("3. Search by Company Car.");
            System.out.println("0. Exit.");
            System.out.print("Enter your choice (1-3): ");
            
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue; // Restart the loop on invalid input
            }
            
            List<Sport_car> searchResults = new ArrayList<>();
            
            switch (choice) {
                case 1:
                    // Search by ID
                    int id = -1;
                    while (id < 0) {
                        try {
                            System.out.print("Enter Super Car ID: ");
                            id = Integer.parseInt(sc.nextLine().trim());
                            if (id < 0) {
                                System.out.println("ID cannot be negative. Please enter a valid number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID format. Please enter a valid number.");
                        }
                    }
                    for (Sport_car car : Lscar) {
                        if (car.getId_car() == id) {
                            searchResults.add(car);
                        }
                    }
                    return showSportSearchResults(sc, searchResults);
    
                case 2:
                    // Search by Name
                    String name;
                    do {
                        System.out.print("Enter Super Car name (or part of the name): ");
                        name = sc.nextLine().trim().toLowerCase();
                        if (name.isEmpty()) {
                            System.out.println("Name cannot be empty. Please enter a valid name.");
                        }
                    } while (name.isEmpty());
                    
                    for (Sport_car car : Lscar) {
                        if (car.getName().toLowerCase().contains(name)) {
                            searchResults.add(car);
                        }
                    }
                    return showSportSearchResults(sc, searchResults);
    
                case 3:
                    // Search by Company (Brand) Name
                    String brandName;
                    do {
                        System.out.print("Enter Super Car brand name (or part of the name): ");
                        brandName = sc.nextLine().trim().toLowerCase();
                        if (brandName.isEmpty()) {
                            System.out.println("Brand name cannot be empty. Please enter a valid brand name.");
                        }
                    } while (brandName.isEmpty());
                    
                    for (Sport_car car : Lscar) {
                        if (car.getCompanyCar().toLowerCase().contains(brandName)) {
                            searchResults.add(car);
                        }
                    }
                    return showSportSearchResults(sc, searchResults);
    
                case 0:
                    System.out.println("Exiting search.");
                    return null;
    
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    break; // Continue asking for input
            }
        }
        return null; // In case of exit from the loop, return null
    }

    private Sport_car showSportSearchResults(Scanner sc,List<Sport_car> searchResults) {
        if(searchResults.isEmpty()){
            System.out.println("No Super Cars found matching your search criteria");
            return null;
        }
        // Display search results
        System.out.println("Search Results:");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println((i + 1) + ". ");
            searchResults.get(i).showDetails();
            System.out.println("------------------------------------");
        }
    
        // Allow the user to select a car from the search results
        int selection = -1;
        while (selection < 0 || selection > searchResults.size()) {
            try {
                System.out.print("Enter the number of the car to select (or 0 to cancel): ");
                selection = Integer.parseInt(sc.nextLine().trim());
                if (selection < 0 || selection > searchResults.size()) {
                    System.out.println("Invalid selection. Please enter a number between 0 and " + searchResults.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter a valid number.");
            }
        }
    
        if (selection > 0) {
            Sport_car selectedCar = searchResults.get(selection - 1);
            System.out.println("You selected: " + selectedCar.getName());
            return selectedCar;
        } else {
            System.out.println("Selection cancelled.");
            return null;
        }
    }

}
