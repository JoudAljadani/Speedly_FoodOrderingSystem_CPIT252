
public class ApplePayPayment implements PaymentStrategy {

    private String phoneNumber;

    public ApplePayPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " SAR using Apple Pay linked to phone number " + phoneNumber);
    }
}