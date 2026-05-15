package Speedly_CPIT252;

public class CustomerNotification implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Customer Notification: Your order is now " + order.getStatus());
    }
}
