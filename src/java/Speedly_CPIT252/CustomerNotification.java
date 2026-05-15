import java.util.ArrayList;
import java.util.List;

public class CustomerNotification extends OrderObserver {

    private List<String> notifications = new ArrayList<>();

    public CustomerNotification(Order order) {
        this.order = order;
        this.order.addObserver(this);
    }

    @Override
    public void update() {
        notifications.add("Order ID " + order.getOrderId()
                + ": Your order is now " + order.getStatus());
    }

    public boolean belongsTo(Customer customer) {
        return order.getCustomer().getId() == customer.getId();
    }

    public void showNotifications() {
        for (String message : notifications) {
            System.out.println("- " + message);
        }
    }

    public boolean hasNotifications() {
        return !notifications.isEmpty();
    }
}