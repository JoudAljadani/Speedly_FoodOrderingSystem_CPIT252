package Speedly_CPIT252.Observer;

import Speedly_CPIT252.OrderManagement.Order;

import java.util.ArrayList;
import java.util.List;

public class StaffNotification extends OrderObserver {

    //list to store all staff notifications
    private List<String> notifications = new ArrayList<>();

    public StaffNotification(Order order) {
        this.order = order;
        this.order.addObserver(this); //add this notification observer to the order
    }

    //add notification when order status changes
    @Override
    public void update() {
        notifications.add("Order ID " + order.getOrderId() + ": Status changed to " + order.getStatus());
    }

    //display all stored notifications
    public void showNotifications() {
        for (String message : notifications) {
            System.out.println("- " + message);
        }
    }

    //check if notification list is not empty
    public boolean hasNotifications() {
        return !notifications.isEmpty();
    }
}