public class Employee extends User {

    public Employee(String name, String phoneNumber, String email, String password) {
        super(name, phoneNumber, email, password);
    }

    public void updateOrderStatus(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.nextState();
    }
}