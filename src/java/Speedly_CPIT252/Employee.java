package Speedly_CPIT252;

public class Employee extends User {

    public Employee(String name, String phoneNumber, String email) {
        super(name, phoneNumber, email);
    }

    public void updateOrderStatus(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.nextState();
    }
}