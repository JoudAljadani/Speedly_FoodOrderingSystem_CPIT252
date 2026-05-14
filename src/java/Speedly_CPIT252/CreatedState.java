public class CreatedState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new PaidState());
    }

    @Override
    public String getStatusName() {
        return "Created";
    }
}