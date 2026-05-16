package Speedly_CPIT252;

import Speedly_CPIT252.OrderManagement.*;
import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderBuilderTest {

    @Test
    void shouldBuildOrderWithCorrectTotal() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Jana",
                "0512345678", "Joud@gmail.com", "1234");

        //create products using ProductFactory
        Product pizza = ProductFactory.createProduct("Cheese Pizza");
        Product water = ProductFactory.createProduct("Water");

        //build an order
        Order order = new OrderBuilder().setCustomer((Customer) customer).addItem(pizza, 2)
                .addItem(water, 1).build();

        assertEquals(customer, order.getCustomer()); //check that customer was assigned correctly
        assertEquals(2, order.getItems().size()); //check number of items added to the order
        assertEquals(53.0, order.getTotalPrice()); //check calculated total price
        assertEquals("Created", order.getStatus()); //check initial order state
    }

    @Test
    void orderWithoutCustomerShouldThrowException() {

        //create product
        Product water = ProductFactory.createProduct("Water");

        //verifying that building order without customer throws exception
        assertThrows(IllegalArgumentException.class, () -> {new OrderBuilder()
                .addItem(water, 1).build(); });
    }

    @Test
    void orderWithoutItemsShouldThrowException() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Jana",
                "0512345678", "Joud@gmail.com", "1234");

        //verifying that building empty order throws exception
        assertThrows(IllegalArgumentException.class, () -> {new OrderBuilder()
                .setCustomer((Customer)customer).build(); });
    }

    @Test
    void invalidQuantityShouldThrowException() {

        //create customer object
        User customer = UserFactory.createUser("customer", "Jana",
                "0512345678", "Joud@gmail.com", "1234");

        //create product
        Product water = ProductFactory.createProduct("Water");

        //verifying that invalid quantity throws exception
        assertThrows(IllegalArgumentException.class, () -> {new OrderBuilder()
                .setCustomer((Customer)customer).addItem(water, 0); });
    }
}