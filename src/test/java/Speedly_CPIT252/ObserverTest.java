package Speedly_CPIT252;

import Speedly_CPIT252.OrderManagement.*;
import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.*;
import Speedly_CPIT252.Observer.*;
import Speedly_CPIT252.Strategy.CashPayment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    @Test
    void customerNotificationShouldUpdateWhenOrderIsPaid() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Jana",
                "0512345678", "Joud@gmail.com", "1234");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer) customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //create customer notification observer
        CustomerNotification notification = new CustomerNotification(order);

        //check that notification list is initially empty
        assertFalse(notification.hasNotifications());

        //set Cash payment strategy and move order from Created to Paid after execution
        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        //check that notification was added after order status update
        assertTrue(notification.hasNotifications());

        //check that notification belongs to the correct customer
        assertTrue(notification.belongsTo((Customer)customer));
    }

    @Test
    void staffNotificationShouldUpdateWhenOrderStatusChanges() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Leena",
                "0512345678", "Joud@gmail.com", "1234");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //create staff notification observer
        StaffNotification notification = new StaffNotification(order);

        //check that notification list is initially empty
        assertFalse(notification.hasNotifications());

        //set Cash payment strategy and move order from Created to Paid after execution
        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        //check that staff notification was added
        assertTrue(notification.hasNotifications());
    }
}