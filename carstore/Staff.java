package carstore;

import java.util.Scanner;

public class Staff extends Person{
    private double salary;

    //Constructor:
    public Staff(int id, String name, String email, String password, String contactNumber,Location address, double salary) {
        super(id, name, email, password, contactNumber,address);
        this.salary = salary;
    }

    //Getter and setter
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    //convert into text to write to file
    @Override
    public String toString() {
        return super.toString()+"\t"+salary+"\n";
    }

    //show the staff's infomation details
    @Override
    public void showDetails(){
        super.showDetails();
        System.out.println("Salary: "+salary);
    }
    
    @Override
    public boolean login(String email, String password){
        if(this.getEmail().equals(email)){
            if (this.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changePassword(Scanner sc){
        System.out.println("------Staff Password Recovery------");
        System.out.println("Create a new password: ");
        String newPassword = ClientValidator.isPasswordValid(sc);
        System.out.println("Confirm your password: ");
        String confirmPassword = ClientValidator.isPasswordValid(sc);
        if(newPassword==confirmPassword){
           setPassword(newPassword);
           System.out.println("Password changed successfully.");
        }
   }
}
