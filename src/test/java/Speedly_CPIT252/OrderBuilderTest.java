import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderBuilderTest {

    @Test
    void shouldBuildOrderWithCorrectTotal() {
        Customer customer = new Customer("Jana", "0512345678",
                "jana@gmail.com", "1234");

        Product pizza = ProductFactory.createProduct("Cheese Pizza");
        Product water = ProductFactory.createProduct("Water");

        Order order = new OrderBuilder()
                .setCustomer(customer)
                .addItem(pizza, 2)
                .addItem(water, 1)
                .build();

        assertEquals(customer, order.getCustomer());
        assertEquals(2, order.getItems().size());
        assertEquals(53.0, order.getTotalPrice());
        assertEquals("Created", order.getStatus());
    }

    @Test
    void orderWithoutCustomerShouldThrowException() {
        Product water = ProductFactory.createProduct("Water");

        assertThrows(IllegalArgumentException.class, () -> {
            new OrderBuilder()
                    .addItem(water, 1)
                    .build();
        });
    }

    @Test
    void orderWithoutItemsShouldThrowException() {
        Customer customer = new Customer("Jana", "0512345678",
                "jana@gmail.com", "1234");

        assertThrows(IllegalArgumentException.class, () -> {
            new OrderBuilder()
                    .setCustomer(customer)
                    .build();
        });
    }

    @Test
    void invalidQuantityShouldThrowException() {
        Customer customer = new Customer("Jana", "0512345678",
                "jana@gmail.com", "1234");

        Product water = ProductFactory.createProduct("Water");

        assertThrows(IllegalArgumentException.class, () -> {
            new OrderBuilder()
                    .setCustomer(customer)
                    .addItem(water, 0);
        });
    }
}