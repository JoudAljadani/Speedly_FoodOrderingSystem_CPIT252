package Speedly_CPIT252;

public class InProgressState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new ReadyForPickupState());
    }

    @Override
    public String getStatusName() {
        return "In Progress";
    }
}