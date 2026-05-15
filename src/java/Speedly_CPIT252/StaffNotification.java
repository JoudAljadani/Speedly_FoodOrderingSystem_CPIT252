package Speedly_CPIT252;

public class StaffNotification implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println("Staff Notification: Order status changed to " + order.getStatus());
    }
}
