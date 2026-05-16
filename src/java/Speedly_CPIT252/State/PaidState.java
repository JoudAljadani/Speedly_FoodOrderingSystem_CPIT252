package Speedly_CPIT252.State;

import Speedly_CPIT252.OrderManagement.Order;

//PaidState
public class PaidState implements OrderState {

    //next state
    @Override
    public void next(Order order) {
        order.setState(new InProgressState());
    }

    //return current state name
    @Override
    public String getStatusName() {
        return "Paid";
    }
}