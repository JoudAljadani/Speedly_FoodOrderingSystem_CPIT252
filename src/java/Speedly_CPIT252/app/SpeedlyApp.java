package Speedly_CPIT252.app;

import Speedly_CPIT252.AppService.*;
import Speedly_CPIT252.UserInput.InputValidator;

//This is the main class that starts and runs the Speedly system
public class SpeedlyApp {

    public static void main(String[] args) {

        //create the input validator to read and validate user input
        InputValidator input = new InputValidator();

        //create the service classes used in the system
        UserService userService = new UserService(input);
        OrderService orderService = new OrderService(input);

        System.out.println("===== Welcome to Speedly =====");

        //Start the main application menu
        runApp(input, userService, orderService);
    }


//------------------------------------------------------------------------------------------------------------------
    //This method displays the main menu and handles the main user choices
    private static void runApp(InputValidator input, UserService userService, OrderService orderService) {

        while (true) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Show Users");
            System.out.println("4. Exit");

            int choice = input.readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    //open the customer part of the system
                    orderService.customerOrderProcess(userService);
                    break;

                case 2:
                    //open the staff part of the system
                    orderService.staffOrderProcess(userService);
                    break;

                case 3:
                    //display registered customers and the default staff account
                    userService.showUsers();
                    break;

                case 4:
                    //close the program.
                    System.out.println("Thank you for using Speedly!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        }
    }
}