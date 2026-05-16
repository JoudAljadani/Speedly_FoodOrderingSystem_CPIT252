package Speedly_CPIT252.AppService;

import Speedly_CPIT252.OrderManagement.Order;
import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Observer.*;
import Speedly_CPIT252.Strategy.*;
import Speedly_CPIT252.OrderManagement.OrderBuilder;
import Speedly_CPIT252.UserInput.InputValidator;

import java.util.ArrayList;

//This class handles the order process for both customer and staff
public class OrderService {

    //InputValidator is used to read and validate user input
    private InputValidator input;

    //lists to store notifications for customers and staff
    private ArrayList<CustomerNotification> customerNotifications = new ArrayList<>();
    private ArrayList<StaffNotification> staffNotifications = new ArrayList<>();

    //list to store all orders created in the system
    private ArrayList<Order> orders = new ArrayList<>();

    //constructor that receives the input validator
    public OrderService(InputValidator input) {
        this.input = input;
    }

    //----------------------------------------------------------------------------
    //------------------------------ CUSTOMER PART -------------------------------
    //This method handles the customer dashboard and customer order process
    public void customerOrderProcess(UserService userService) {

        //register or login the customer first
        Customer customer = userService.customerAccountProcess();

        while (true) {
            System.out.println("\n===== CUSTOMER DASHBOARD =====");
            System.out.println("Customer: " + customer.getName());

            System.out.println("\n1. Create Order");
            System.out.println("2. View My Notifications");
            System.out.println("3. View My Order History");
            System.out.println("4. Sign Out");

            int choice = input.readInt("Enter choice: ");

            switch (choice) {
                case 1:

                    //get the single shared menu instance using Singleton
                    Menu menu = Menu.getInstance();

                    System.out.println("\n===== MENU =====");
                    menu.displayMenu();

                    //create a new order for the logged in customer
                    Order order = createOrder(menu, customer);

                    //Attach customer and staff notifications to the order using Observer
                    CustomerNotification customerNotification = new CustomerNotification(order);
                    StaffNotification staffNotification = new StaffNotification(order);

                    customerNotifications.add(customerNotification);
                    staffNotifications.add(staffNotification);

                    //let the customer choose a payment method using Strategy
                    choosePayment(order, customer);

                    System.out.println("\n===== PAYMENT PROCESS =====");
                    //pay the order and move its state from Created to Paid
                    order.payOrder();

                    //store the paid order in the orders list
                    orders.add(order);

                    System.out.println("Order created and paid successfully.");
                    System.out.println("Order ID: " + order.getOrderId());
                    System.out.println("Current Order Status: " + order.getStatus());
                    break;

                case 2:
                    //display notifications of the customer
                    showCustomerNotifications(customer);
                    break;

                case 3:
                    //display order history of the customer
                    showCustomerOrderHistory(customer);
                    break;

                case 4:
                    System.out.println("Signed out from customer dashboard.");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        }
    }

    //This method creates an order by allowing the customer to choose products and quantities
    private Order createOrder(Menu menu, Customer customer) {

        //OrderBuilder is used to build the order step by step
        OrderBuilder builder = new OrderBuilder();
        builder.setCustomer(customer);

        String addMore = "yes";

        while (addMore.equals("yes")) {

            //choose a product from the menu
            Product product = chooseProduct(menu);

            int quantity;

            //read a valid quantity from the customer
            while (true) {
                quantity = input.readInt("Enter quantity: ");

                if (quantity > 0) {
                    break;
                }

                System.out.println("Quantity must be greater than 0.");
            }

            //add the selected product and quantity to the order
            builder.addItem(product, quantity);

            addMore = input.readYesNo("Add another item? yes/no: ");
        }

        //build and return the final order object
        return builder.build();
    }

    //lets the customer choose a product from the menu
    private Product chooseProduct(Menu menu) {

        while (true) {
            int itemNumber = input.readInt("\nChoose item number: ");

            try {
                return menu.getProduct(itemNumber - 1);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid item number.");
            }
        }
    }

    //lets the customer choose the payment method
    private void choosePayment(Order order, Customer customer) {

        System.out.println("\n===== PAYMENT =====");
        System.out.println("Your total is: " + order.getTotalPrice() + " SAR");

        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. Apple Pay");

        int choice;

        while (true) {
            choice = input.readInt("Choose payment method: ");
            if (choice >= 1 && choice <= 3) {
                break;
            }

            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }

        switch (choice) {

            case 1:
                //set cash as the payment strategy
                order.setPaymentStrategy(new CashPayment());
                break;

            case 2:
                String cardNumber;

                while (true) {// Keep asking until the customer enters a valid credit card number
                    cardNumber = input.readLine("Enter card number: ");
                    if (CreditCardPayment.isValidCardNumber(cardNumber)) {
                        break;
                    }
                    System.out.println("Invalid card number. Card number must contain exactly 16 digits.");
                }

                //set credit card as the payment strategy
                order.setPaymentStrategy(new CreditCardPayment(cardNumber));
                break;

            case 3:
                //set Apple Pay as the payment strategy using the customer's phone number
                order.setPaymentStrategy(new ApplePayPayment(customer.getPhoneNumber()));
                System.out.println("Apple Pay will use customer phone number: " + customer.getPhoneNumber());
                break;
        }
    }

    //This method displays notifications for the logged in customer
    private void showCustomerNotifications(Customer customer) {
        System.out.println("\n===== CUSTOMER NOTIFICATIONS =====");

        boolean hasAnyNotification = false;

        for (CustomerNotification notification : customerNotifications) {
            if (notification.belongsTo(customer) && notification.hasNotifications()) {
                notification.showNotifications();
                hasAnyNotification = true;
            }
        }

        if (!hasAnyNotification) {
            System.out.println("No notifications yet.");
        }
    }

    //This method displays the order history for the logged in customer
    private void showCustomerOrderHistory(Customer customer) {
        System.out.println("\n===== MY ORDER HISTORY =====");

        boolean found = false;

        for (Order order : orders) {
            if (order.getCustomer().getId() == customer.getId()) {
                System.out.println("Order ID: " + order.getOrderId()
                        + " | Status: " + order.getStatus()
                        + " | Total: " + order.getTotalPrice() + " SAR");
                found = true;
            }
        }

        if (!found) {
            System.out.println("You do not have any orders yet.");
        }
    }

    //----------------------------------------------------------------------------
    //-------------------------------- STAFF PART --------------------------------
    //This method handles the staff dashboard and order status updates
    public void staffOrderProcess(UserService userService) {

        if (orders.isEmpty()) {
            System.out.println("No orders available yet.");
            return;
        }

        //get the default staff account from UserService
        Staff staff = userService.getDefaultStaff();

        while (true) {
            System.out.println("\n===== STAFF ORDER DASHBOARD =====");
            System.out.println("Staff: " + staff.getName());

            System.out.println("\n1. View Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. View Staff Notifications");
            System.out.println("4. Sign Out");

            //let the staff choose which order to update
            int choice = input.readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    //display all orders in the system
                    showOrders();
                    break;

                case 2:
                    Order selectedOrder = chooseOrder();

                    //move the selected order to the next state
                    staff.updateOrderStatus(selectedOrder);

                    System.out.println("Updated Order ID: " + selectedOrder.getOrderId());
                    System.out.println("Updated Order Status: " + selectedOrder.getStatus());
                    break;

                case 3:
                    //displays all staff notifications
                    showStaffNotifications();
                    break;

                case 4:
                    System.out.println("Signed out from staff dashboard.");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 4.");
            }
        }
    }

    //This method displays all orders for the staff
    private void showOrders() {
        System.out.println("\n===== ORDERS LIST =====");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            System.out.println((i + 1) + ". Order ID: " + order.getOrderId()
                    + " | Customer: " + order.getCustomer().getName()
                    + " | Status: " + order.getStatus()
                    + " | Total: " + order.getTotalPrice() + " SAR");
        }
    }

    //lets the staff choose an order to update from the orders list
    private Order chooseOrder() {

        showOrders();

        while (true) {
            int choice = input.readInt("Choose order number: ");

            if (choice >= 1 && choice <= orders.size()) {
                return orders.get(choice - 1);
            }

            System.out.println("Invalid order choice.");
        }
    }

    //This method displays all staff notifications
    private void showStaffNotifications() {
        System.out.println("\n===== STAFF NOTIFICATIONS =====");

        boolean hasAnyNotification = false;

        for (StaffNotification notification : staffNotifications) {
            if (notification.hasNotifications()) {
                notification.showNotifications();
                hasAnyNotification = true;
            }
        }

        if (!hasAnyNotification) {
            System.out.println("No staff notifications yet.");
        }
    }
}