package Speedly_CPIT252;

import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.ProductFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//This class implements the testing of factory method for products name
public class ProductFactoryTest {

    @Test
    void shouldCreatePizzaProduct() {
        //create a pizza product using ProductFactory
        Product product = ProductFactory.createProduct("Cheese Pizza");

        //check that the created product is a Pizza and has the correct data
        assertTrue(product instanceof Pizza);
        assertEquals("Cheese Pizza", product.getName());
        assertEquals(25.0, product.getPrice());
        assertEquals("Pizza", product.getCategory());
    }

    @Test
    void shouldCreateBurgerProduct() {
        //create a burger product using ProductFactory
        Product product = ProductFactory.createProduct("Beef Burger");

        //check that the created product is a Burger and has the correct data
        assertTrue(product instanceof Burger);
        assertEquals("Beef Burger", product.getName());
        assertEquals(25.0, product.getPrice());
        assertEquals("Burger", product.getCategory());
    }

    @Test
    void shouldCreateDrinkProduct() {
        //create a drink product using ProductFactory
        Product product = ProductFactory.createProduct("Water");

        //check that the created product is a Drink and has the correct data
        assertTrue(product instanceof Drink);
        assertEquals("Water", product.getName());
        assertEquals(3.0, product.getPrice());
        assertEquals("Drink", product.getCategory());
    }

    @Test
    void invalidProductShouldThrowException() {
        //check that an invalid product name throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct("Sushi");
        });
    }

    @Test
    void nullProductShouldThrowException() {
        //check that a null product name throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct(null);
        });
    }
}