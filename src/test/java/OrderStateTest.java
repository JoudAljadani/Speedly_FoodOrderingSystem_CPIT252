import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStateTest {

    @Test
    void paymentShouldMoveOrderFromCreatedToPaid() {
        Customer customer = new Customer("Joud", "0512345678",
                "jojo@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        assertEquals("Paid", order.getStatus());
    }

    @Test
    void employeeShouldUpdateOrderStatusStepByStep() {
        Customer customer = new Customer("Jana", "0512345678",
                "jana@gmail.com", "1234");

        Employee employee = new Employee("Speedly Staff", "0500000000",
                "staff@speedly.com", "staff123");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        employee.updateOrderStatus(order);
        assertEquals("In Progress", order.getStatus());

        employee.updateOrderStatus(order);
        assertEquals("Ready for Pickup", order.getStatus());

        employee.updateOrderStatus(order);
        assertEquals("Picked Up", order.getStatus());
    }

    @Test
    void orderCannotBePaidTwice() {
        Customer customer = new Customer("leena", "0512345678",
                "leena@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        assertThrows(IllegalStateException.class, () -> {
            order.payOrder();
        });
    }
}