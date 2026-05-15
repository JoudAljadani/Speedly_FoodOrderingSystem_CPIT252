import java.util.ArrayList;

public class OrderService {

    private InputHelper input;
    private ArrayList<CustomerNotification> customerNotifications = new ArrayList<>();
    private ArrayList<StaffNotification> staffNotifications = new ArrayList<>();

    private ArrayList<Order> orders = new ArrayList<>();

    public OrderService(InputHelper input) {
        this.input = input;
    }

    public void customerOrderProcess(UserService userService) {

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
                    Menu menu = Menu.getInstance();

                    System.out.println("\n===== MENU =====");
                    menu.displayMenu();

                    Order order = createOrder(menu, customer);

                    CustomerNotification customerNotification = new CustomerNotification(order);
                    StaffNotification staffNotification = new StaffNotification(order);

                    customerNotifications.add(customerNotification);
                    staffNotifications.add(staffNotification);

                    choosePayment(order, customer);

                    System.out.println("\n===== PAYMENT PROCESS =====");
                    order.payOrder();

                    orders.add(order);

                    System.out.println("Order created and paid successfully.");
                    System.out.println("Order ID: " + order.getOrderId());
                    System.out.println("Current Order Status: " + order.getStatus());
                    break;

                case 2:
                    showCustomerNotifications(customer);
                    break;

                case 3:
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

    public void staffOrderProcess(UserService userService) {

        if (orders.isEmpty()) {
            System.out.println("No orders available yet.");
            return;
        }

        Employee employee = userService.getDefaultEmployee();

        while (true) {
            System.out.println("\n===== STAFF ORDER DASHBOARD =====");
            System.out.println("Staff: " + employee.getName());

            System.out.println("\n1. View Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. View Staff Notifications");
            System.out.println("4. Sign Out");

            int choice = input.readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    showOrders();
                    break;

                case 2:
                    Order selectedOrder = chooseOrder();

                    employee.updateOrderStatus(selectedOrder);

                    System.out.println("Updated Order ID: " + selectedOrder.getOrderId());
                    System.out.println("Updated Order Status: " + selectedOrder.getStatus());
                    break;

                case 3:
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

    private Order createOrder(Menu menu, Customer customer) {

        OrderBuilder builder = new OrderBuilder();
        builder.setCustomer(customer);

        String addMore = "yes";

        while (addMore.equals("yes")) {

            Product product = chooseProduct(menu);

            int quantity;

            while (true) {
                quantity = input.readInt("Enter quantity: ");

                if (quantity > 0) {
                    break;
                }

                System.out.println("Quantity must be greater than 0.");
            }

            builder.addItem(product, quantity);

            addMore = input.readYesNo("Add another item? yes/no: ");
        }

        return builder.build();
    }

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
                order.setPaymentStrategy(new CashPayment());
                break;

            case 2:
                String cardNumber;

                while (true) {
                    cardNumber = input.readLine("Enter card number: ");

                    if (cardNumber.length() == 16) {
                        break;
                    }

                    System.out.println("Invalid card number. Card number must contain exactly 16 digits.");
                }

                order.setPaymentStrategy(new CreditCardPayment(cardNumber));
                break;

            case 3:
                order.setPaymentStrategy(new ApplePayPayment(customer.getPhoneNumber()));
                System.out.println("Apple Pay will use customer phone number: " + customer.getPhoneNumber());
                break;
        }
    }

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
}