package Speedly_CPIT252.UserInput;

import java.util.Scanner;

//This class is used to read and validate user input from the console
public class InputValidator {

    //Scanner object to read input from the user.
    private Scanner input = new Scanner(System.in);

    //reads an integer value and keeps asking until the user enters a valid number
    public int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    //reads the user's name and makes sure it is not empty
    public String readName() {
        while (true) {
            System.out.print("Enter name: ");
            String name = input.nextLine();

            if (!name.trim().isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be empty.");
        }
    }

    //reads the phone number and checks that it starts with 05 and has 10 digits
    public String readPhone() {
        while (true) {
            System.out.print("Enter phone number: ");
            String phone = input.nextLine();

            if (phone.length() == 10 && phone.startsWith("05")) {
                return phone;
            }

            System.out.println("Invalid phone number. It must start with 05 and contain 10 digits");
        }
    }

    //reads the email and checks that it has a simple valid email format
    public String readEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = input.nextLine();

            if (email.contains("@") && email.contains(".")) {
                return email;
            }

            System.out.println("Invalid email format. Example: name@gmail.com");
        }
    }

    //reads the password and checks that it has at least 4 characters
    public String readPassword() {
        while (true) {
            System.out.print("Enter password: ");
            String password = input.nextLine();

            if (password.length() >= 4) {
                return password;
            }

            System.out.println("Password must be at least 4 characters.");
        }
    }

    //reads yes or no answers and keeps asking until the input is valid
    public String readYesNo(String message) {
        while (true) {
            System.out.print(message);
            String answer = input.nextLine().trim().toLowerCase();

            if (answer.equals("yes") || answer.equals("no")) {
                return answer;
            }

            System.out.println("Please enter yes or no.");
        }
    }

    //reads a normal line from the user
    public String readLine(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    //closes the scanner when the program ends
    public void close() {
        input.close();
    }
}