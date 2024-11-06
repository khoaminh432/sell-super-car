package carstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * author: lyhieunghia
 */

public class CustomerManager implements IFeatures {
    private List<Customer> cList;
    Scanner sc;
    
    //Constructor
    public CustomerManager() {
        cList = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    /* Display the Customer List:BEGIN
     * parameter: nothing
     * return: nothing
     * Description: -show the list of the customers
     */
    public void display(){
        if(cList.isEmpty()){
            System.out.println("Nothing to display.");
            return;
        }

        for(Customer customer:cList){
            System.out.println("------------------------------------");
            customer.showDetails();
            System.out.println("------------------------------------");
        }
    }
    //Display: END

    /* ReadFromFile:BEGIN
     * parameter:nothing
     * return: nothing
     * Description: -read the customer list from "Customer.txt"
     */
    @Override
    public void readFromFile() throws FileNotFoundException, IOException{
        File f = new File("Customer.txt");
        //Create a new file if file is not found
        if(!f.exists()){
            f.createNewFile();
            System.out.println("New file created!");
        }

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        while (true) {
            String line = br.readLine();
            if(line==null) break;

            //infomation
            String info[] = line.split("\t");
            int id =Integer.parseInt(info[0].trim());
            String name=info[1].trim();
            String email=info[2].trim();
            String password=info[3].trim();
            String contact=info[4].trim();
            
            //address
            String houseNumber = info[5].trim();
            String street = info[6].trim();
            String ward = info[7].trim();
            String district = info[8].trim();
            String city = info[9].trim();
            Location address = new Location(houseNumber,street,ward,district,city);

            //add to list
            cList.add(new Customer(id,name,email,password,contact,address));
        }

        fr.close();
        br.close();
    }
    //ReadFromFile:END

    /*
     * 
     * 
     * 
     */
    @Override
    public void writeToFile() throws IOException,FileNotFoundException{
        File f = new File("Customer.txt");
        if(!f.exists()){
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Customer c:cList){
            bw.write(c.toString());
        }

        fw.close();
        bw.close();
    }
    //WriteToFile:END

    public void add(){
    }

    public void delete(){

    }

    public void update(){

    }

    

    

    
}
