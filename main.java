import department_car.Super_car;
import java.io.*;
public class main {
    public static void main(String[] args) {
        
        try {
            FileWriter fwriter = new FileWriter("list_car.txt");
            Super_car sc = new Super_car("lambo",10000,12000,150f,3f,1.5f,1,"lamborgini",10);
            fwriter.write(sc.toString()); 
            fwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}