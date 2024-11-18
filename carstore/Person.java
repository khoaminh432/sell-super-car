package carstore;

import java.util.Scanner;

/**
 * author: lyhieunghia
 * 
 */

 public abstract class Person 
 {
   private int id;
   private String name, email, password, contactNumber;
   private Location address;

//Contructor
   public Person(){
   }

   public Person(int id, String name, String email, String password, String contactNumber,Location address) {
      this.id = id;
      this.name = name;
      this.address = address;
      this.email = email;
      this.password = password;
      this.contactNumber = contactNumber;
   }



   //Getter and Setter
   public void setID(int id){
      this.id=id;
   }

   public int getID(){
      return id;
   }

   public void setName(String name ){
      this.name= name;
   }
   
   public String getName(){
      return name;
   }      
   
   public void setAddress(Location address){
      this.address=address;
   }
   
   public Location getAddress(){
      return address;
   }
   
   public void setContactNumber(String contactNumber ){
      this.contactNumber=contactNumber;
   }
   
   public String getContactNumber(){
      return contactNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   //convert the person's infomation into string to store
   
   public String toString(){
      return id+"\t"+name+"\t"+email+"\t"+password+"\t"+contactNumber+"\t"+this.address.toString();
   }
   
   //show the person's infomation details
   
   public void showDetails(){
      System.out.println("ID Number: "+id);
      System.out.println("Name: "+name);
      System.out.println("Email Address: "+email);
      System.out.println("Contact Number: " +contactNumber);
      address.showDetails();
   }

   public abstract void changePassword(Scanner sc);

   public abstract boolean login(String email, String password);
   
}

