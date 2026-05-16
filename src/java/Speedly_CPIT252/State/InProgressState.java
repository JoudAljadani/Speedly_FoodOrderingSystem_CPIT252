package Speedly_CPIT252.State;

import Speedly_CPIT252.OrderManagement.Order;

//InProgressState
public class InProgressState implements OrderState {

    //next state
    @Override
    public void next(Order order) {
        order.setState(new ReadyForPickupState());
    }

    //return current state name
    @Override
    public String getStatusName() {
        return "In Progress";
    }
}