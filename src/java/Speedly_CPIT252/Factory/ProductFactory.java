package Speedly_CPIT252.Factory;

import Speedly_CPIT252.Entity.*;

//This class implements Factory method design pattern
public class ProductFactory {

    //This method creates and returns the correct product object based on the product name
    public static Product createProduct(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Product name cannot be null");
        }

        switch (name.toLowerCase()) {

            //Pizza products
            case "cheese pizza":
                return new Pizza("Cheese Pizza", 25);

            case "veggie pizza":
                return new Pizza("Veggie Pizza", 27);

            //Burgers products
            case "beef burger":
                return new Burger("Beef Burger", 25);

            case "chicken burger":
                return new Burger("Chicken Burger", 20);

            //Appetizers products
            case "french fries":
                return new Appetizer("French Fries", 10);

            case "mozzarella sticks":
                return new Appetizer("Mozzarella Sticks", 17);

            //Drinks products
            case "apple juice":
                return new Drink("Apple Juice", 8);

            case "orange juice":
                return new Drink("Orange Juice", 9);

            case "water":
                return new Drink("Water", 3);

            case "pepsi":
                return new Drink("Pepsi", 6);

            case "7up":
                return new Drink("7UP", 6);

            default:
                throw new IllegalArgumentException("Invalid product name");
        }
    }
}
