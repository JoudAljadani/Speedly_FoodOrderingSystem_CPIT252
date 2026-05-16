package Speedly_CPIT252.Strategy;

//Cash payment
public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " SAR using Cash.");
    }
}