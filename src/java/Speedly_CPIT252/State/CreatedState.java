package Speedly_CPIT252.State;

import Speedly_CPIT252.OrderManagement.Order;

//CreatedState
public class CreatedState implements OrderState {

    //next state
    @Override
    public void next(Order order) {
        order.setState(new PaidState());
    }

    //return current state name
    @Override
    public String getStatusName() {
        return "Created";
    }
}