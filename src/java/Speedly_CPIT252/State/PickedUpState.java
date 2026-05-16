package Speedly_CPIT252.State;

import Speedly_CPIT252.OrderManagement.Order;

//PickedUpState
public class PickedUpState implements OrderState {

    //order completed
    @Override
    public void next(Order order) {
        System.out.println("Order is already picked up. No next status.");
    }

    //return current state name
    @Override
    public String getStatusName() {
        return "Picked Up";
    }
}