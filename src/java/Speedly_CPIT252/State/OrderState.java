package Speedly_CPIT252.State;

import Speedly_CPIT252.OrderManagement.Order;

//State interface for all order states
public interface  OrderState {
    void next(Order order); //moves the order to the next state
    String getStatusName(); //returns the current state name
}