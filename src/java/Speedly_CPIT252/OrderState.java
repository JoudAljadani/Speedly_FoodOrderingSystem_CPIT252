package Speedly_CPIT252;

public interface  OrderState {

        void next(Order order);

        String getStatusName();

}
