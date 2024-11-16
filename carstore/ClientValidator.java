package carstore;

import java.util.Scanner;

public class ClientValidator {
    
    //NAME VALIDATOR: BEGIN
   public static String isNameValid(Scanner sc) {
    while(true){
        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        //A valid name can only contain at least two words that are capitalized.
        if(!name.matches("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+\s[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?:\s[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*"))
        {
            System.out.println("Invalid Name!");
            System.out.println("A valid name can only contain at least two words that are capitalized.");
            continue;
        }

        //We only allows names that are not more than 50 letters.
        if(name.length()>50){
            System.out.println("Your name is too long!");
            System.out.println("We only allows names that are not more than 50 letters.");
            continue;
        }
        return name;
    }
   }
    //NAME VALIDATOR: END

    //PHONE NUMBER VALIDATOR: BEGIN
    public static String isContactNumberValid(Scanner sc){
        while(true){
            System.out.println("Enter your phone number: ");
            String number = sc.nextLine();

            //a valid contact number has 10 numbers and starts with 0.
            if (!number.matches("0" + "[0-9]{9}")) {
                System.out.println("Phone number is invalid.");
                System.out.println("A valid contact number has 10 numbers and starts with 0.");
                continue;
            }

            return number;
        }
    }
    //PHONE NUMBER VALIDATOR: END

    //EMAIL VALIDATOR: BEGIN
    public static String isEmailAdressValid(Scanner sc){
        while(true){
            System.out.println("Enter your email: ");
            String email = sc.nextLine();

            //Use RFC 5322 to check if the email is valid.
            if(!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                System.out.println("Your email is invalid.");
                continue;
            }
        return email;
        }
    }
    //EMAIL VALIDATOR: END

    //PASSWORD VALIDATOR: BEGIN
    public static String isPasswordValid(Scanner sc){
        while(true){
            System.out.println("Enter your password: ");
            String password = sc.nextLine();

            //There are at least 8 chars
            if(!password.matches(".{8,}")){
                System.out.println("The password must contain at least 8 characters.");
                continue;
            }

            //A digit must occur at least once
            if(!password.matches("(?=.*[0-9])")){
                System.out.println("The password must contain at least 1 digit.");
                continue;
            }

            //A lowercase letter must occur at least once
            if(!password.matches("(?=.*[a-z])")){
                System.out.println("The password must contain at least 1 lowercase letter.");
                continue;
            }

            //An uppercase letter must occur at least once
            if(!password.matches("(?=.*[A-Z])")){
                System.out.println("The password must contain at least 1 uppercase letter.");
                continue;
            }

            //A special character must occur at least once
            if(!password.matches("(?=.*[@#$%^&+=])")){
                System.out.println("The password must contain at least 1 special character.");
                continue;
            }

            //No whitespace allowed in the password.
            if(!password.matches("(?=\\S+$)")){
                System.out.println("No whitespace allowed in the password.");
                continue;
            }
            return password;
        }
    }
    //PASSWORD VALIDATOR: END

    public static double isSalaryValid(Scanner sc) {
        double salary;
        while (true) {
            System.out.print("Enter salary:");
            try {
                String input = sc.nextLine().trim();
                salary = Double.parseDouble(input);
            
                if (salary >= 0) {
                    return salary;
                } else {
                    System.out.println("Salary must be a non-negative number. Please try again.");
                }
            } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric salary.");
            }
        }
    }
}
