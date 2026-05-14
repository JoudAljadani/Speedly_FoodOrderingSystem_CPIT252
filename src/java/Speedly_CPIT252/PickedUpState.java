public class PickedUpState implements OrderState {

    @Override
    public void next(Order order) {
        System.out.println("Order is already picked up. No next status.");
    }

    @Override
    public String getStatusName() {
        return "Picked Up";
    }
}