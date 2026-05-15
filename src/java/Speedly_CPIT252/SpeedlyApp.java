package Speedly_CPIT252;

import java.util.Scanner;

public class SpeedlyApp {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("===== Welcome to Speedly =====");

        User user = createUser();

        Menu menu = Menu.getInstance();

        System.out.println("\n===== MENU =====");
        menu.displayMenu();

        Order order = createOrder(menu);

        addObservers(order, user);

        choosePayment(order, user);

        processOrder(order);

        input.close();
    }

    public static User createUser() {

        int choice;

        while (true) {
            System.out.println("Choose user type:");
            System.out.println("1. Customer");
            System.out.println("2. Employee");

            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                if (choice == 1 || choice == 2) {
                    break;
                }

                System.out.println("Invalid choice. Please enter 1 or 2.\n");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.\n");
            }
        }

        String name;

        while (true) {
            System.out.print("Enter your name: ");
            name = input.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty.");
            } else {
                break;
            }
        }

        String phone;

        while (true) {
            System.out.print("Enter your phone number: ");
            phone = input.nextLine();

            if (phone.length() != 10 || !phone.startsWith("05")) {
                System.out.println("Invalid phone number. It must start with 05 and contain 10 digits.");
            } else {
                break;
            }
        }

        String email;

        while (true) {
            System.out.print("Enter your email: ");
            email = input.nextLine();

            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Invalid email format. Example: name@gmail.com");
            } else {
                break;
            }
        }

        if (choice == 1) {
            return UserFactory.createUser("customer", name, phone, email);
        } else {
            return UserFactory.createUser("employee", name, phone, email);
        }
    }

    public static Order createOrder(Menu menu) {

        Order.Builder builder = new Order.Builder();
        String addMore = "yes";

        while (addMore.equalsIgnoreCase("yes")) {

            int itemNumber;

            while (true) {
                System.out.print("\nChoose item number: ");
                String itemInput = input.nextLine();

                try {
                    itemNumber = Integer.parseInt(itemInput);

                    if (menu.getProduct(itemNumber - 1) == null) {
                        System.out.println("Invalid item number.");
                        continue;
                    }

                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Please enter one valid number.");
                }
            }

            Product product = menu.getProduct(itemNumber - 1);

            int quantity;

            while (true) {
                System.out.print("Enter quantity: ");
                String quantityInput = input.nextLine();

                try {
                    quantity = Integer.parseInt(quantityInput);

                    if (quantity < 1) {
                        System.out.println("Quantity must be greater than 0.");
                        continue;
                    }

                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Please enter one valid quantity.");
                }
            }

            builder.addItem(product, quantity);

            while (true) {
                System.out.print("Add another item? yes/no: ");
                addMore = input.nextLine().trim().toLowerCase();

                if (addMore.equals("yes") || addMore.equals("no")) {
                    break;
                }

                System.out.println("Please enter yes or no.");
            }
        }

        return builder.build();
    }

    public static void addObservers(Order order, User user) {

        if (user instanceof Customer) {
            order.addObserver(new CustomerNotification());

        } else if (user instanceof Employee) {
            order.addObserver(new StaffNotification());
        }
    }

    public static void choosePayment(Order order, User user) {

        System.out.println("\n===== PAYMENT =====");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. Apple Pay");

        int choice;

        while (true) {
            System.out.print("Choose payment method: ");

            try {
                choice = Integer.parseInt(input.nextLine());

                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                }

                System.out.println("Invalid choice. Please enter 1, 2, or 3.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        switch (choice) {

            case 1:
                order.setPaymentStrategy(new CashPayment());
                break;

            case 2:
                String cardNumber;

                while (true) {
                    System.out.print("Enter card number: ");
                    cardNumber = input.nextLine();

                    if (cardNumber.length() != 16) {
                        System.out.println("Invalid card number. Card number must contain exactly 16 digits.");
                    } else {
                        break;
                    }
                }

                order.setPaymentStrategy(new CreditCardPayment(cardNumber));
                break;

            case 3:
                order.setPaymentStrategy(new ApplePayPayment(user.getPhoneNumber()));
                System.out.println("Apple Pay will use your registered phone number: " + user.getPhoneNumber());
                break;
        }
    }

    public static void processOrder(Order order) {

        System.out.println("\n===== PAYMENT PROCESS =====");

        order.payOrder();

        Employee employee = (Employee) UserFactory.createUser("employee", "Staff",
                "0500000000", "staff@speedly.com");

        try {
            Thread.sleep(2000);
            employee.updateOrderStatus(order);

            Thread.sleep(2000);
            employee.updateOrderStatus(order);

            Thread.sleep(2000);
            employee.updateOrderStatus(order);

        } catch (InterruptedException e) {
            System.out.println("Order process interrupted.");
        }

        System.out.println("\nFinal Order Status: " + order.getStatus());
    }
}