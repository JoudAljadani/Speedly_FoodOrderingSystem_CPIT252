import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    @Test
    void customerNotificationShouldUpdateWhenOrderIsPaid() {
        Customer customer = new Customer("Jana", "0512345678",
                "jana@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        CustomerNotification notification = new CustomerNotification(order);

        assertFalse(notification.hasNotifications());

        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        assertTrue(notification.hasNotifications());
        assertTrue(notification.belongsTo(customer));
    }

    @Test
    void staffNotificationShouldUpdateWhenOrderStatusChanges() {
        Customer customer = new Customer("leena", "0512345678",
                "leena@gmail.com", "1234");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(ProductFactory.createProduct("Water"), 1)
                .build();

        StaffNotification notification = new StaffNotification(order);

        assertFalse(notification.hasNotifications());

        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        assertTrue(notification.hasNotifications());
    }
}