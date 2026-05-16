package Speedly_CPIT252.Strategy;

//Each payment method implements this strategy interface
public interface PaymentStrategy {
    void pay(double amount);
}