package department_car;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import carstore.IdManager;

public class List_luxury_car extends List_Car {
    private ArrayList<Luxury_car> Lxcar;
    private IdManager id_car = new IdManager(ID_FILE_NAME);
    private static final String LUXURYCAR_FILE_NAME = "Data/luxuryCar.txt";
    private static final String ID_FILE_NAME = "Data/luxeryCarID.txt";
    public List_luxury_car() {
        super();
        Lxcar = new ArrayList<>();
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
    

    @Override
    public void WriteFile(String filename){
        try{
            FileWriter fw = new FileWriter(filename);
            for(Luxury_car lc: Lxcar){
                fw.write(lc.toString()+"\n");
            }
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("");
        }
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
    
    
    
    public void menuForManager(Scanner scanner){
        ReadFile(LUXURYCAR_FILE_NAME);
        int choice;
        do{
            System.out.println("====================MENU===============");
            System.out.println("1.Add a new luxury car");
            System.out.println("2.Remove car");
            System.out.println("3.Change the informations of car");
            System.out.println("4.Search car");
            System.out.println("5.Show the informations of cars");
            System.out.println("6.Save");
            System.out.println("0.Exit");
            choice = scanner.nextInt();
        
            switch(choice){
                case 1:
                    add(scanner);
                    break;
                case 2:
                    System.out.println("=============REMOVE============");
                    System.out.println("1.Remove by Id");
                    System.out.println("2.Remove by name");
                    System.out.println("Enter your choice (1-2): ");
                    int choose;
                    choose = scanner.nextInt();
                    switch(choose){
                        case 1:
                            System.out.println("Enter name: ");
                            String name1 = scanner.nextLine().trim();
                            Super_car sc = Search_name(name1);
                            Remove(sc);
                            break;
                        case 2:
                            System.out.println("Enter id: ");
                            int id1 = scanner.nextInt();
                            Super_car spc = Search_idcar(id1);
                            Remove(spc);
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose a valid option.");
                    }
                case 3:
                    System.out.println("===============CHANGE============");
                    System.out.println("1.Change by name ");
                    System.out.println("2.Change by Id");
                    System.out.println("Enter your choice(1-2):");
                    choose = scanner.nextInt();
                    int position;
                    if(choose == 1)
                    {
                        System.out.println("Enter name: ");
                        String name = scanner.nextLine().trim();
                        position = getindex(name);
                    }else{
                        System.out.println("Enter Id car: ");
                        int idCar = scanner.nextInt();
                        position = getindex(idCar);
                    }
                    do{
                            System.out.println("===============CHANGE OPTION============");
                            System.out.println("1.Change All");
                            System.out.println("2.Change Id");
                            System.out.println("3.Change Pricesell");
                            System.out.println("4.Change Pricebuy");
                            System.out.println("5.Change Lenght");
                            System.out.println("6.Change Weight");
                            System.out.println("7.Change Height");
                            System.out.println("8.Change Width");
                            System.out.println("9.Change InteriorMaterial");
                            System.out.println("10.Change ClimateControl");
                            System.out.println("11.Change SoundSystem");
                            System.out.println("12.Change SafetyFeatures");
                            System.out.println("0.Exit");
                            System.out.println("Enter your choice(1-12):");
                            choose = scanner.nextInt();
                            switch(choose){
                                case 1:
                                    System.out.println("Id car: ");
                                    int id = scanner.nextInt();
                                    System.out.println("Name: ");
                                    String name2 = scanner.nextLine();
                                    System.out.println("Pricesell: ");
                                    int pricesell = scanner.nextInt();
                                    System.out.println("Pricebuy: ");
                                    int pricebuy = scanner.nextInt();
                                    System.out.println("Lenght: ");
                                    float length = scanner.nextFloat();
                                    System.out.println("Height: ");
                                    float height = scanner.nextFloat();
                                    System.out.println("Weight: ");
                                    float weight = scanner.nextFloat();
                                    System.out.println("Width: ");
                                    float width = scanner.nextFloat();
                                    System.out.println("Company: ");
                                    String company = scanner.nextLine();
                                    System.out.println("The number of cars: ");
                                    int number = scanner.nextInt();
                                    System.out.println("Interior Material: ");
                                    String interior = scanner.nextLine();
                                    System.out.println("Climate Control: ");
                                    String climate = scanner.nextLine();
                                    System.out.println("Sound System: ");
                                    String sound = scanner.nextLine();
                                    System.out.println("Safety Features:");
                                    String safety = scanner.nextLine();
                                    
                                    Luxury_car lc = new Luxury_car(id,name2,pricebuy,pricesell,weight,length,height,width,number,company,interior,climate,sound,safety);
                                    set(position,lc);
                                    break;
                                case 2:
                                    System.out.println("Id car: ");
                                    int id2 = scanner.nextInt();
                                    setId_car(position,id2);
                                    break;
                                case 3:
                                    System.out.println("Pricesell: ");
                                    int price = scanner.nextInt();
                                    setPricesell(position,price);
                                    break;
                                case 4:
                                    System.out.println("Pricebuy: ");
                                    int price2 = scanner.nextInt();
                                    setPricebuy(position,price2);
                                    break;   
                                case 5:
                                    System.out.println("Length: ");
                                    float l = scanner.nextFloat();
                                    setLength(position,l);
                                    break;
                                case 6:
                                    System.out.println("Weight: ");
                                    float w = scanner.nextFloat();
                                    setWeight(position,w);
                                    break;
                                case 7:
                                    System.out.println("Height: ");
                                    float h = scanner.nextFloat();
                                    setHeight(position,h);
                                    break;
                                case 8:
                                    System.out.println("Width: ");
                                    float wid = scanner.nextFloat();
                                    setWidth(position,wid);
                                    break;
                                case 9:
                                    System.out.println("Interior Material: ");
                                    String interiorMaterial = scanner.nextLine().trim();
                                    setInteriorMaterial(position,interiorMaterial);
                                    break;
                                case 10:
                                    System.out.println("Climate Control: ");
                                    String climateControl = scanner.nextLine().trim();
                                    setClimateControl(position,climateControl);
                                    break;
                                case 11:
                                    System.out.println("Sound System: ");
                                    String soundSystem = scanner.nextLine().trim();
                                    setClimateControl(position,soundSystem);
                                    break;
                                case 12:
                                    System.out.println("Safety Features: ");
                                    String safetyFeatures = scanner.nextLine().trim();
                                    setClimateControl(position,safetyFeatures);
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                    }while(choose != 0);
                    break;
                case 4:
                    System.out.println("=============SEARCH==========");
                    System.out.println("1.Search by Id");
                    System.out.println("2.Search by name");
                    System.out.println("Enter your choice(1-2): ");
                    int choose4 = scanner.nextInt();
                    if(choose4 == 1)
                    {
                        System.out.println("Enter Name: ");
                        String name4 = scanner.nextLine().trim();
                        Super_car sc4 = Search_name(name4);
                        sc4.showDetails();
                    }else{
                        System.out.println("Enter Id: ");
                        int idCar4 = scanner.nextInt();
                        Super_car sc5 = Search_idcar(idCar4);
                        sc5.showDetails();
                    }
                    break;
                case 5:
                    System.out.println("=============LIST CAR==================");
                    for(Luxury_car car : Lxcar){
                        car.showDetails();
                    }
                    break;
                case 6:
                    WriteFile(LUXURYCAR_FILE_NAME);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
                        
        }while(choice !=0);
    }
}
