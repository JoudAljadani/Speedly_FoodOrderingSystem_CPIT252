package Speedly_CPIT252.State;

import Speedly_CPIT252.OrderManagement.Order;

//ReadyForPickupState
public class ReadyForPickupState implements OrderState {

    //next state
    @Override
    public void next(Order order) {
        order.setState(new PickedUpState());
    }

    //return current state name
    @Override
    public String getStatusName() {
        return "Ready for Pickup";
    }
}