public class SpeedlyApp {

    public static void main(String[] args) {

        InputHelper input = new InputHelper();
        UserService userService = new UserService(input);
        OrderService orderService = new OrderService(input);

        System.out.println("===== Welcome to Speedly =====");

        runApp(input, userService, orderService);
    }

    private static void runApp(InputHelper input, UserService userService, OrderService orderService) {

        while (true) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Show Users");
            System.out.println("4. Exit");

            int choice = input.readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    orderService.customerOrderProcess(userService);
                    break;

                case 2:
                    orderService.staffOrderProcess(userService);
                    break;

                case 3:
                    userService.showUsers();
                    break;

                case 4:
                    System.out.println("Thank you for using Speedly!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        }
    }
}