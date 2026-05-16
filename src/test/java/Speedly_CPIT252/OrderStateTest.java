package Speedly_CPIT252;

import Speedly_CPIT252.OrderManagement.*;
import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.*;
import Speedly_CPIT252.Strategy.CashPayment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStateTest {

    @Test
    void paymentShouldMoveOrderFromCreatedToPaid() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        //build order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //set Cash payment strategy and move order from Created to Paid once payment is executed
        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        //verify state changed from Created to Paid
        assertEquals("Paid", order.getStatus());
    }

    @Test
    void staffShouldUpdateOrderStatusStepByStep() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Jana", "0512345678",
                "jana@gmail.com", "1234");

        //create staff object responsible for updating order status
        Staff staff = new Staff("Speedly Staff", "0500000000",
                "staff@speedly.com", "staff123");

        //build order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //set Cash payment strategy and move order from Created to Paid once payment is executed
        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        //staff updates order to In Progress
        staff.updateOrderStatus(order);
        //verify state updated correctly
        assertEquals("In Progress", order.getStatus());

        //staff updates order to Ready for Pickup
        staff.updateOrderStatus(order);
        //verify state updated correctly
        assertEquals("Ready for Pickup", order.getStatus());

        //staff updates order to Picked Up
        staff.updateOrderStatus(order);
        //verify final state
        assertEquals("Picked Up", order.getStatus());
    }

    @Test
    void orderCannotBePaidTwice() {

        //create customer
        User customer = UserFactory.createUser("customer", "leena", "0512345678",
                "leena@gmail.com", "1234");

        //build order
        Order order = new OrderBuilder().setCustomer((Customer)customer)
                .addItem(ProductFactory.createProduct("Water"), 1).build();

        //first payment moves order to Paid state
        order.setPaymentStrategy(new CashPayment());
        order.payOrder();

        //verify second payment is not allowed
        assertThrows(IllegalStateException.class, () -> {
            order.payOrder();
        });
    }
}