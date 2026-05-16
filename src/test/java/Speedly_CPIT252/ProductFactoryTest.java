package Speedly_CPIT252;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {

    @Test
    void shouldCreatePizzaProduct() {
        Product product = ProductFactory.createProduct("Cheese Pizza");

        assertTrue(product instanceof Pizza);
        assertEquals("Cheese Pizza", product.getName());
        assertEquals(25.0, product.getPrice());
        assertEquals("Pizza", product.getCategory());
    }

    @Test
    void shouldCreateBurgerProduct() {
        Product product = ProductFactory.createProduct("Beef Burger");

        assertTrue(product instanceof Burger);
        assertEquals("Beef Burger", product.getName());
        assertEquals(25.0, product.getPrice());
        assertEquals("Burger", product.getCategory());
    }

    @Test
    void shouldCreateDrinkProduct() {
        Product product = ProductFactory.createProduct("Water");

        assertTrue(product instanceof Drink);
        assertEquals("Water", product.getName());
        assertEquals(3.0, product.getPrice());
        assertEquals("Drink", product.getCategory());
    }

    @Test
    void invalidProductShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct("Sushi");
        });
    }
}