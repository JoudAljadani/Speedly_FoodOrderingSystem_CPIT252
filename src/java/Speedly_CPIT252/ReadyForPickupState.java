package Speedly_CPIT252;

public class ReadyForPickupState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new PickedUpState());
    }

    @Override
    public String getStatusName() {
        return "Ready for Pickup";
    }
}