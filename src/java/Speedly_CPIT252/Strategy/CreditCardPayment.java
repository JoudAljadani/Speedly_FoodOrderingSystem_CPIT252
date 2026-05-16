package Speedly_CPIT252.Strategy;

//Credit card payment
public class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Card number must contain exactly 16 digits");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " SAR using Credit Card ending with "
                + cardNumber.substring(cardNumber.length() - 4));
    }

    //Check if the credit card number is valid
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null
                && cardNumber.length() == 16
                && cardNumber.matches("\\d+");
    }
}