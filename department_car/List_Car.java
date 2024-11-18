package department_car;

import carstore.IdManager;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class List_Car implements Ishowfor{
    private ArrayList<Super_car> Lcar;
    protected static final String SUPERCAR_FILE_NAME = "Data/list_car.txt";
    private static final String ID_FILE_NAME = "Data/superCarID.txt";
    private IdManager id_car = new IdManager(ID_FILE_NAME);
    public List_Car(){
        Lcar = new ArrayList<>();
    }
    //thêm phần tử vào cuối mảng
    public void add(Super_car sc){
        Lcar.add(sc);
    }
    
    //thêm phần tử tại vị trí
    public void add(int index,Super_car sc){
        Lcar.add(index,sc);
    }
    //thêm vào id còn trống
    public void add(Scanner scanner){
        System.out.println("=================ADD=============");
        int id = id_car.idGenerator();
        String name = scanner.nextLine().trim();
        int pricesell = scanner.nextInt();
        int pricebuy = scanner.nextInt();
        float weight = scanner.nextFloat();
        float length = scanner.nextFloat();
        float height = scanner.nextFloat();
        float width = scanner.nextFloat();
        int quantityof_car = scanner.nextInt();
        String CompanyCar = scanner.nextLine().trim();
        add(new Super_car(id,name,pricesell,pricebuy,weight,length,height,width,quantityof_car,CompanyCar));
    }
    //xoa
    public void Remove(Super_car sc){
        Lcar.remove(sc);
        id_car.releaseId(sc.getId_car());
    }
    // hàm xóa phàn tử tại vị trí index
    public void remove(int index){
        Lcar.remove(index);
    }
    public void output(){
        for (Super_car sc: Lcar)
            System.out.println(sc);
    }
    public ArrayList<Super_car> getList(){
        return Lcar;
    }
    public Super_car get(int index){
        return Lcar.get(index);
    }
    public int getindex(int id_car){
        for(int index = 0;index<Lcar.size();index++)
            if (Lcar.get(index).getId_car() == id_car)
                return index;
        return -1;
    }
    public int getindex(String name){
        for(int index=0;index<Lcar.size();index++)
            if(Lcar.get(index).getName().equals(name))
                return index;
        return -1;
    }
    
    //sửa 
    public void set(int index,Super_car sc){
        Lcar.set(index,sc);
    }
    public void setId_car(int index,int id_car){
        Lcar.set(index,Lcar.get(index)).setId_car(id_car);
    }
    public void setLcar(ArrayList<Super_car> lcar) {
        Lcar = lcar;
    }
    public void setHeight(int index,float height){
        Lcar.set(index,Lcar.get(index)).setHeight(height);
    }
    public void setLength(int index,float length){
        Lcar.set(index,Lcar.get(index)).setLength(length);
    }
    public void setWidth(int  index,float width){
        Lcar.set(index,Lcar.get(index)).setWidth(width);
    }
    public void setWeight(int  index,float weight){
        Lcar.set(index,Lcar.get(index)).setWeight(weight);
    }
    public void setPricebuy(int  index,int pricebuy){
        Lcar.set(index,Lcar.get(index)).setPricebuy(pricebuy);
    }
    public void setPricesell(int  index,int pricesell){
        Lcar.set(index,Lcar.get(index)).setPricesell(pricesell);
    }
    public void setCompanyCar(int  index,String CompanyCar){
        Lcar.set(index,Lcar.get(index)).setCompanyCar(CompanyCar);
        
    }

    // tìm kiếm
    public Super_car Search_idcar(int id_car){
        for(Super_car spc: Lcar)
            if (spc.getId_car() == id_car)
                return spc;
        return null;
    }
    
    public ArrayList<Super_car> Search_name(String name){
        ArrayList<Super_car> results = new ArrayList<>();
        for(Super_car spc: Lcar)
            if (spc.getName().toLowerCase().contains(name))
                results.add(spc);
        return results;

    }
    public void search_company(String companycar){
        int number_of_SuperCar = 0;
        for(Super_car sCar: Lcar)
        if(sCar.getCompanyCar().equals(companycar))
        if(Luxury_car.checkLuxuryCar(sCar))
        {sCar.showforCustomer();number_of_SuperCar++;}
        else if(Sport_car.checkSportCar(sCar))
        {sCar.showforCustomer();number_of_SuperCar++;}
        else if(sCar instanceof Super_car){sCar.showforCustomer();number_of_SuperCar++;}
        System.out.println("Quantity SUper Car: "+number_of_SuperCar);
    }
    @Override
    public void showDetails() {
        for(Super_car sc: Lcar){
            if(Luxury_car.checkLuxuryCar(sc))
                sc.showDetails();
            else if(Sport_car.checkSportCar(sc))
                sc.showDetails();
            else sc.showDetails();
        }
    }

    @Override
    public void showforCustomer() {
        for(Super_car sc:Lcar)
        {   
            if(Luxury_car.checkLuxuryCar(sc))
                sc.showforCustomer();
            else if(Sport_car.checkSportCar(sc))
                sc.showforCustomer();
            else sc.showforCustomer();
            }
    }
    
    public void WriteFile(final String filename){
        try{
            FileWriter fw = new FileWriter(filename);
            for(Super_car sc: Lcar){
                fw.write(sc.toString()+"\n");
            }
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void AppendFile(final String filename){
        try{
            FileWriter fw = new FileWriter(filename);
            for(Super_car sc: Lcar){
                fw.append(sc.toString()+"\n");
            }
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void  ReadFile(final String SUPERCAR_FILE_NAME){
        ArrayList<String> arr = new ArrayList<>();
        try {
            FileReader fw = new FileReader(SUPERCAR_FILE_NAME);
            int data = fw.read();
            String name="";
            while(data !=-1){
                
                name += (char)data;
                if(data=='\n')
                {
                    arr.add(name.replace("\n","" ));
                    name = "";
                }
                
                data = fw.read();
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String supercar: arr){
            ArrayList<String> carindex = new ArrayList<>(Arrays.asList(supercar.split("\t")));
            
            if(carindex.size()==14){ if(Car.DoubleNumber(carindex.get(11)))
                add(new Sport_car(carindex));
            else
                add(new Luxury_car(carindex));}
            else if(carindex.size() == 10) 
                add(new Super_car(carindex));
            else continue;
            System.out.println("car index: "+carindex.size());
        }
        
    }

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
                    Super_car super_carToRemove = search(scanner);
                    if (super_carToRemove != null) {
                        Remove(super_carToRemove);
                    }
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 3:
                    Super_car super_carToUpdate = search(scanner);
                    if (super_carToUpdate != null) {
                        super_carToUpdate.showDetails();
                        update(scanner, super_carToUpdate);
                    }
                    WriteFile(SUPERCAR_FILE_NAME);
                    break;
    
                case 4:
                    Super_car carToSearch = search(scanner);
                    if (carToSearch != null) {
                        carToSearch.showDetails();
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
    
                case 5:
                    System.out.println("=============LIST CAR==================");
                    for (Super_car car : Lcar) {
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
    
    private void update(Scanner scanner, Super_car sc) {
        int choose = -1;
        do {
            System.out.println("===============CHANGE OPTION============");
            System.out.println("1. Change All");
            System.out.println("3. Change Pricesell");
            System.out.println("4. Change Pricebuy");
            System.out.println("5. Change Length");
            System.out.println("6. Change Weight");
            System.out.println("7. Change Height");
            System.out.println("8. Change Width");
            System.out.println("0. Exit");
            System.out.println("Enter your choice(1-8):");
            choose = scanner.nextInt();
    
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
                    scanner.nextLine();  // consume the newline
                    String newCompany = scanner.nextLine().trim();
                    System.out.println("Enter new Quantity: ");
                    int newQuantity = scanner.nextInt();
    
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
                    break;
    
                case 3:
                    System.out.println("Enter new Price Sell: ");
                    int priceSell = scanner.nextInt();
                    sc.setPricesell(priceSell);
                    break;
    
                case 4:
                    System.out.println("Enter new Price Buy: ");
                    int priceBuy = scanner.nextInt();
                    sc.setPricebuy(priceBuy);
                    break;
    
                case 5:
                    System.out.println("Enter new Length: ");
                    float length = scanner.nextFloat();
                    sc.setLength(length);
                    break;
    
                case 6:
                    System.out.println("Enter new Weight: ");
                    float weight = scanner.nextFloat();
                    sc.setWeight(weight);
                    break;
    
                case 7:
                    System.out.println("Enter new Height: ");
                    float height = scanner.nextFloat();
                    sc.setHeight(height);
                    break;
    
                case 8:
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

    public Super_car search(Scanner sc) {
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
            
            List<Super_car> searchResults = new ArrayList<>();
            
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
                    for (Super_car car : Lcar) {
                        if (car.getId_car() == id) {
                            searchResults.add(car);
                        }
                    }
                    return showSearchResults(sc, searchResults);
    
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
                    
                    for (Super_car car : Lcar) {
                        if (car.getName().toLowerCase().contains(name)) {
                            searchResults.add(car);
                        }
                    }
                    return showSearchResults(sc, searchResults);
    
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
                    
                    for (Super_car car : Lcar) {
                        if (car.getCompanyCar().toLowerCase().contains(brandName)) {
                            searchResults.add(car);
                        }
                    }
                    return showSearchResults(sc, searchResults);
    
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
    
    private Super_car showSearchResults(Scanner sc,List<Super_car> searchResults) {
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
            Super_car selectedCar = searchResults.get(selection - 1);
            System.out.println("You selected: " + selectedCar.getName());
            return selectedCar;
        } else {
            System.out.println("Selection cancelled.");
            return null;
        }
    }

    public Luxury_car searchLuxuryCar(Scanner sc){
        List_luxury_car list_luxury_car = new List_luxury_car();
        list_luxury_car.setList(Lcar);
        return list_luxury_car.searchLXCar(sc);
    }

    public Sport_car searchSportCar(Scanner sc){
        List_sport_car list_sport_car = new List_sport_car();
        list_sport_car.setList(Lcar);
        return list_sport_car.searchSPCar(sc);
    }

    public void showLxrCarMenuForManager(Scanner sc){
        List_luxury_car list_luxury_car = new List_luxury_car();
        list_luxury_car.setList(Lcar);
        list_luxury_car.menuForManager(sc);
    }
    
    public void showSptCarMenuForManager(Scanner sc){
        List_sport_car list_sport_car = new List_sport_car();
        list_sport_car.setList(Lcar);
        list_sport_car.menuForManager(sc);
    }
}
    