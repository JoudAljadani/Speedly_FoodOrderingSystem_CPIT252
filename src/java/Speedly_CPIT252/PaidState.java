package Speedly_CPIT252;

public class PaidState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new InProgressState());
    }

    @Override
    public String getStatusName() {
        return "Paid";
    }
}