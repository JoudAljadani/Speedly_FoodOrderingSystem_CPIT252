package Speedly_CPIT252;

import Speedly_CPIT252.OrderManagement.*;
import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.*;
import Speedly_CPIT252.Strategy.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStrategyTest {

    @Test
    void cashPaymentShouldPayOrder() {

        //create customer object for the test
        User customer = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //use Cash payment strategy
        order.setPaymentStrategy(new CashPayment());

        //execute payment
        order.payOrder();

        //check if payment updated order status
        assertEquals("Paid", order.getStatus());
    }

    @Test
    void creditCardPaymentShouldPayOrder() {

        //create customer object for the test
        User customer = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Pepsi"), 2).build();

        //use CreditCard payment strategy
        order.setPaymentStrategy(new CreditCardPayment("1234567890123456"));

        //execute payment
        order.payOrder();

        //check if payment updated order status
        assertEquals("Paid", order.getStatus());
    }

    @Test
    void applePayPaymentShouldPayOrder() {

        //create customer object for the test
        User customer = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Apple Juice"), 1).build();

        //use customer's phone number for Apple Pay
        order.setPaymentStrategy(new ApplePayPayment(customer.getPhoneNumber()));

        //execute payment
        order.payOrder();

        //check if payment updated order status
        assertEquals("Paid", order.getStatus());
    }

    @Test
    void validCreditCardNumberShouldReturnTrue() {
        //16-digit card number
        assertTrue(CreditCardPayment.isValidCardNumber("1234567890123456"));
    }

    @Test
    void shortCreditCardNumberShouldReturnFalse() {
        //card number is too short
        assertFalse(CreditCardPayment.isValidCardNumber("123"));
    }

    @Test
    void creditCardWithLettersShouldReturnFalse() {
        //card number contains letters
        assertFalse(CreditCardPayment.isValidCardNumber("12345678901234AB"));
    }

    @Test
    void nullCreditCardNumberShouldReturnFalse() {
        //null card number should be invalid
        assertFalse(CreditCardPayment.isValidCardNumber(null));
    }

    @Test
    void creditCardWithShortNumberShouldThrowException() {

        //verify invalid short card number throws exception
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCardPayment("123"); });
    }

    @Test
    void creditCardWithLettersShouldThrowException() {

        //verify card numbers with letters throws exception
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCardPayment("12345678901234AB");});
    }

    @Test
        //Create order without setting payment strategy
    void orderWithoutPaymentStrategyShouldThrowException() {

        //create customer object for the test
        User customer = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //verify paying without strategy throws exception
        assertThrows(IllegalStateException.class, () -> {
            order.payOrder();});
    }
}