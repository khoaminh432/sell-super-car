package department_car;



import carstore.IdManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class List_luxury_car extends List_Car {
    private ArrayList<Luxury_car> Lxcar;
    private IdManager id_car = new IdManager(ID_FILE_NAME);
    private List_Car lcar = new List_Car();
    
    protected  static final String LUXURYCAR_FILE_NAME = "Data/luxuryCar.txt";
    private static final String ID_FILE_NAME = "Data/luxuryCarID.txt";
    
    public List_luxury_car() {
        super();
        Lxcar = new ArrayList<>();
        lcar.ReadFile(List_Car.SUPERCAR_FILE_NAME);
        setList(lcar.getList());
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
        System.out.println("Inerior Material: ");String InteriorMaterial = scanner.nextLine().trim();
        System.out.println("Climate Control: ");String ClimateControl = scanner.nextLine().trim();
        System.out.println("Sound System: ");String SoundSystem = scanner.nextLine().trim();
        System.out.println("Safety Feeatures: ");String SafetyFeatures = scanner.nextLine().trim();
        Luxury_car newCar = new Luxury_car(id,name,pricebuy,pricesell,weight,length,height,width,quantityof_car,CompanyCar,InteriorMaterial,SoundSystem,ClimateControl,SafetyFeatures);
        Lxcar.add(newCar);
    }
    
    // tạo ds chỉ luxury car từ list super car
    public void setList(ArrayList<Super_car> sc){
        for(Super_car sCar:sc)
            if(Luxury_car.checkLuxuryCar(sCar))
                super.getList().add(sCar);
    }

    public ArrayList<Luxury_car> getLxcar() {
        return Lxcar;
    }

    public static String getLuxurycarFileName() {
        return LUXURYCAR_FILE_NAME;
    }

    public static String getIdFileName() {
        return ID_FILE_NAME;
    }

    public ArrayList<Luxury_car> getLxcar() {
        return Lxcar;
    }

    public static String getLuxurycarFileName() {
        return LUXURYCAR_FILE_NAME;
    }

    public static String getIdFileName() {
        return ID_FILE_NAME;
    }

    
    public void setInteriorMaterial(int index,String interior_material){
        ((Luxury_car)super.getList().get(index)).setInteriorMaterial(interior_material);
    }
    public void setClimateControl(int index,String climate_control){
        ((Luxury_car)super.getList().get(index)).setClimateControl(climate_control);
    }
    public void setSoundSystem(int index,String sound_system){
        ((Luxury_car)super.getList().get(index)).setSoundSystem(sound_system);
    }
    public void setSafetyFeatures(int index,String safety_features){
        ((Luxury_car)super.getList().get(index)).setSafetyFeatures(safety_features);
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
                    Luxury_car carToRemove = searchLXCar(scanner);
                    if (carToRemove != null) {
                        Super_car super_carToRemove = (Super_car) carToRemove;
                        Remove(super_carToRemove);
                    }
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 3:
                    Luxury_car super_carToUpdate = searchLXCar(scanner);
                    if (super_carToUpdate != null) {
                        super_carToUpdate.showDetails();
                        update(scanner, super_carToUpdate);
                    }
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 4:
                    Luxury_car carToSearch = searchLXCar(scanner);
                    if (carToSearch != null) {
                        carToSearch.showDetails();
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
    
                case 5:
                    System.out.println("=============LIST CAR==================");
                    for (Luxury_car car : Lxcar) {
                        car.showDetails();
                    }
                    break;
    
                case 6:
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
                        
        }while(choice !=0);
    }
    
    private void update(Scanner scanner, Luxury_car sc) {
        int choose = -1;
        do {
            System.out.println("===============CHANGE OPTION============");
            System.out.println("1. Change All");
            System.out.println("2. Change Interior Material");
            System.out.println("3. Change Sound System");
            System.out.println("4. Change Climate Control");
            System.out.println("5. Change Safety Features");
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
                    scanner.nextLine();  // Consume newline
                    String newCompany = scanner.nextLine().trim();
                    System.out.println("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
    
                    // Update all the values
                    sc.setName(newName);
                    sc.setPricesell(newPricesell);
                    sc.setPricebuy(newPricebuy);
                    sc.setLength(newLength);
                    sc.setHeight(newHeight);
                    sc.setWeight(newWeight);
                    sc.setWidth(newWidth);
                    sc.setCompanyCar(newCompany);
                    sc.setQuantityof_car(newQuantity);
    
                    // Update Luxury Car-specific attributes
                    System.out.println("Enter new Interior Material: ");
                    String newInteriorMaterial = scanner.nextLine().trim();
                    System.out.println("Enter new Sound System: ");
                    String newSoundSystem = scanner.nextLine().trim();
                    System.out.println("Enter new Climate Control: ");
                    String newClimateControl = scanner.nextLine().trim();
                    System.out.println("Enter new Safety Features: ");
                    String newSafetyFeatures = scanner.nextLine().trim();
    
                    sc.setInteriorMaterial(newInteriorMaterial);
                    sc.setSoundSystem(newSoundSystem);
                    sc.setClimateControl(newClimateControl);
                    sc.setSafetyFeatures(newSafetyFeatures);
    
                    break;
    
                case 2:
                    System.out.println("Enter new Interior Material: ");
                    String interiorMaterial = scanner.nextLine().trim();
                    sc.setInteriorMaterial(interiorMaterial);
                    break;
    
                case 3:
                    System.out.println("Enter new Sound System: ");
                    String soundSystem = scanner.nextLine().trim();
                    sc.setSoundSystem(soundSystem);
                    break;
    
                case 4:
                    System.out.println("Enter new Climate Control: ");
                    String climateControl = scanner.nextLine().trim();
                    sc.setClimateControl(climateControl);
                    break;
    
                case 5:
                    System.out.println("Enter new Safety Features: ");
                    String safetyFeatures = scanner.nextLine().trim();
                    sc.setSafetyFeatures(safetyFeatures);
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

    public Luxury_car searchLXCar(Scanner sc) {
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
            
            List<Luxury_car> searchResults = new ArrayList<>();
            
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
                    for (Luxury_car car : Lxcar) {
                        if (car.getId_car() == id) {
                            searchResults.add(car);
                        }
                    }
                    return showLuxurySearchResults(sc, searchResults);
    
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
                    
                    for (Luxury_car car : Lxcar) {
                        if (car.getName().toLowerCase().contains(name)) {
                            searchResults.add(car);
                        }
                    }
                    return showLuxurySearchResults(sc, searchResults);
    
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
                    
                    for (Luxury_car car : Lxcar) {
                        if (car.getCompanyCar().toLowerCase().contains(brandName)) {
                            searchResults.add(car);
                        }
                    }
                    return showLuxurySearchResults(sc, searchResults);
    
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

    private Luxury_car showLuxurySearchResults(Scanner sc,List<Luxury_car> searchResults) {
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
            Luxury_car selectedCar = searchResults.get(selection - 1);
            System.out.println("You selected: " + selectedCar.getName());
            return selectedCar;
        } else {
            System.out.println("Selection cancelled.");
            return null;
        }
    }
}