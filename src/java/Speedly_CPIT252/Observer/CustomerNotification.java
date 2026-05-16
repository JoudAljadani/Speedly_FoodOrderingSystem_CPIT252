package Speedly_CPIT252.Observer;

import Speedly_CPIT252.Entity.Customer;
import Speedly_CPIT252.OrderManagement.Order;
import java.util.ArrayList;
import java.util.List;

public class CustomerNotification extends OrderObserver {

    //list to store all customer notifications
    private List<String> notifications = new ArrayList<>();

    public CustomerNotification(Order order) {
        this.order = order;
        this.order.addObserver(this); //add this notification observer to the order
    }

    //add notification when order status changes
    @Override
    public void update() {
        notifications.add("Order ID " + order.getOrderId() + ": Your order is now " + order.getStatus());
    }

    //check if the order belongs to this customer
    public boolean belongsTo(Customer customer) {
        return order.getCustomer().getId() == customer.getId();
    }

    //display saved notifications
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