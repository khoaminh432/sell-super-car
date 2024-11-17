package department_car;

import carstore.IdManager;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class List_Car implements Ishowfor{
    private ArrayList<Super_car> Lcar;
    private Scanner scanner ;
    private static final String SUPERCAR_FILE_NAME = "Data/superCar.txt";
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
    public void add(){
        scanner = new Scanner(System.in);
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
        scanner.close();
    }
    //xoa
    public void Remove(Super_car sc){
        Lcar.remove(sc);
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
    
    public Super_car Search_name(String name){
        for(Super_car spc: Lcar)
            if (spc.getName().equals(name))
                return spc;
        return null;
    }
    public void search_company(String name){
        int number_of_SuperCar = 0;
        for(Super_car sCar: Lcar)
        if(sCar.getName()==name)
        if(Luxury_car.checkLuxuryCar(sCar)){
        sCar.showforCustomer();
        number_of_SuperCar++;}
    else if(Sport_car.checkSportCar(sCar)){
        sCar.showforCustomer();number_of_SuperCar++;}
    else {sCar.showforCustomer();number_of_SuperCar++;}
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
    
    public void WriteFile(final String SUPERCAR_FILE_NAME){
        try{
            FileWriter fw = new FileWriter(SUPERCAR_FILE_NAME);
            for(Super_car sc: Lcar){
                fw.write(sc.toString()+"\n");
            }
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void AppendFile(final String SUPERCAR_FILE_NAME){
        try{
            FileWriter fw = new FileWriter(SUPERCAR_FILE_NAME);
            for(Super_car sc: Lcar){
                fw.append(sc.toString()+"\n");
            }
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> ReadFile(final String SUPERCAR_FILE_NAME){
        ArrayList<String> arr = new ArrayList<>();
        try {
            FileReader fw = new FileReader(SUPERCAR_FILE_NAME);
            int data = fw.read();
            String name="";
            while(data !=-1){
                
                name += (char)data;
                if(data=='\n')
                {
                    arr.add(name);
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
            if (carindex.size()==10)
                add(new Super_car(carindex));
            else if(Car.DoubleNumber(carindex.get(11)))
                add(new Sport_car(carindex));
            else
                add(new Luxury_car(carindex));
        }
        return arr;
    }
    public void menuForManager(){
        scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("====================MENU===============");
            System.out.println("1.Add a new super car");
            System.out.println("2.Remove car");
            System.out.println("3.Change the informations of car");
            System.out.println("4.Search car");
            System.out.println("5.Show the informations of cars");
            System.out.println("6.Output to file");
            System.out.println("0.Exit");
            choice = scanner.nextInt();
        
            switch(choice){
                case 1:
                    add();
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
                            System.out.println("0.Exit");
                            System.out.println("Enter your choice(1-8):");
                            choose = scanner.nextInt();
                            switch(choose){
                                case 1:
                                    System.out.println("Id car: ");
                                    int id = scanner.nextInt();
                                    System.out.println("Name: ");
                                    String name2 = scanner.nextLine().trim();
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
                                    Super_car sc = new Super_car(id,name2,pricebuy,pricesell,weight,length,height,width,number,company);
                                    set(position,sc);
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
                    for(Super_car car : Lcar){
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
        scanner.close();
    }
    private void menu(){
        System.out.println("===================Welcome customer====================");
        System.out.println("1. Display car list.");
        System.out.println("2. Search from car list.");
        System.out.println("3. Show your profile.");
        System.out.println("4. Edit your profile.");
        System.out.println("5. Buy Car");
        System.out.println("0. Exit.");
        System.out.print("Your choose: ");
        
    }
    public void menusearch(){
        System.out.println("1. Search by Id.");
        System.out.println("2. Search by name.");
        System.out.println("3. Search by Company Car.");
        System.out.println("4. Search by Luxury Car.");
        System.out.println("5. Search by Sport Car.");
        System.out.println("0. Exit.");
        System.out.print("Your choose: ");
    }
    public static void decorateheader(String contend){
        System.out.println("================================="+contend+"=================================");
    }
    public static void decoratefooter(){
        System.out.println("===============================================================================");
    }
    public void menuForCustomer(){
        scanner = new Scanner(System.in);
        int choose;
        
        do{
            menu();
            choose = Car.chooseInteger(scanner.nextLine());
            ReadFile("Data/list_car.txt");
            switch (choose) {
                case 1:
                decorateheader("Super Car");
                    Super_car.quantitycar();
                    showforCustomer();
                decoratefooter();
                    break;
                case 2:
                    
                    do { 
                        menusearch();
                        choose = Car.chooseInteger(scanner.nextLine());
                        
                        switch (choose) {
                            case 1:
                            decorateheader("Search ID car");
                            do{try{System.out.print("Enter ID Car: ");
                                int id = Car.chooseInteger(scanner.nextLine());
                                Search_idcar(id).showforCustomer();
                            break;}catch(Exception e){
                            System.out.println("Invalid ID");}
                            }while(true);
                            decoratefooter();
                                break;
                            case 2:
                            decorateheader("Search Name Car");
                            do{try{System.out.print("Enter name Car: ");
                                String name= scanner.nextLine();
                                Search_name(name).showforCustomer();break;}catch(Exception e){
                                    System.out.println("Invalid Name");
                                }}while(true);
                            decoratefooter();
                                break; 
                            case 3:
                            decorateheader("Search Company Car");
                            do{try{System.out.print("Enter name Company Car: ");
                                String company = scanner.nextLine();
                                search_company(company);break;}catch(Exception e){
                                    System.out.println("Invalid Company Car");
                            }}while(true);
                            decoratefooter();
                                break;
                            case 4:
                            decorateheader("Search Luxury Car");
                                Luxury_car.quantitycar();
                                List_luxury_car listlucar= new List_luxury_car();
                                listlucar.setList(Lcar); 
                                listlucar.showforCustomer();
                            decoratefooter();
                                break;
                            case 5:
                            decorateheader("Search Sport Car");
                            Sport_car.quantitycar();
                            List_sport_car listspcar= new List_sport_car();
                            listspcar.setList(Lcar);        
                            listspcar.showforCustomer();
                            decoratefooter();
                            break;
                            default: System.out.println("Invalid Choose");
                        }
                        if(choose==0)
                            {choose = -1;
                            break;}
                    } while (true);
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                decorateheader("Buy Super Car");
                    System.out.print("You want buy Car: (y/n)");
                    char buyString = scanner.next().charAt(0);
                    if (buyString=='n')
                        {System.out.println("Thank you for much");
                        break;}
                    else if(buyString=='y')
                        {
                        System.out.println();
                        }
                    
                decoratefooter();
                    break;
                default:decoratefooter();
                    
            }
            if(choose==0)
                break;
                
        }while(true);
    scanner.close();
    }
    
    
}