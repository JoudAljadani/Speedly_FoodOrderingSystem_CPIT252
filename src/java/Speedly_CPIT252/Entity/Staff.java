package Speedly_CPIT252.Entity;

import Speedly_CPIT252.OrderManagement.Order;

public class Staff extends User {

    //calls the parent constructor to store the staff's information
    public Staff(String name, String phoneNumber, String email, String password) {
        super(name, phoneNumber, email, password);
    }

    //Updates the order status by moving it to the next state (state design pattern)
    public void updateOrderStatus(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.nextState();
    }
}