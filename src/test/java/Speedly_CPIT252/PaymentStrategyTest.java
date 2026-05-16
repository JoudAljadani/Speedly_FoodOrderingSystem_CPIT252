package Speedly_CPIT252;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStrategyTest {

    @Test
    void cashPaymentShouldPayOrder() {
        Customer customer = new Customer("Joud", "0512345678",
                "Joud@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        assertEquals("Paid", order.getStatus());
    }

    @Test
    void creditCardPaymentShouldPayOrder() {
        Customer customer = new Customer("Joud", "0512345678",
                "Joud@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Pepsi"), 2)
                .build();

        order.setPaymentStrategy(new CreditCardPayment("1234567890123456"));
        order.payOrder();

        assertEquals("Paid", order.getStatus());
    }

    @Test
    void applePayPaymentShouldPayOrder() {
        Customer customer = new Customer("Joud", "0512345678",
                "Joud@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Apple Juice"), 1)
                .build();

        order.setPaymentStrategy(new ApplePayPayment(customer.getPhoneNumber()));
        order.payOrder();

        assertEquals("Paid", order.getStatus());
    }

    @Test
    void creditCardWithShortNumberShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditCardPayment("123");
        });
    }

    @Test
    void orderWithoutPaymentStrategyShouldThrowException() {
        Customer customer = new Customer("Joud", "0512345678",
                "Joud@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        assertThrows(IllegalStateException.class, () -> {
            order.payOrder();
        });
    }
}