import java.util.ArrayList;
import java.util.List;

public class StaffNotification extends OrderObserver {

    private List<String> notifications = new ArrayList<>();

    public StaffNotification(Order order) {
        this.order = order;
        this.order.addObserver(this);
    }

    @Override
    public void update() {
        notifications.add("Order ID " + order.getOrderId()
                + ": Status changed to " + order.getStatus());
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