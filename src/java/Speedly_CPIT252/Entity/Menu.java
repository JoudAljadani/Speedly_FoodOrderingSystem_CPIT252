package Speedly_CPIT252.Entity;

import Speedly_CPIT252.Factory.ProductFactory;

import java.util.ArrayList;

//This class implements the Singleton Design Pattern
public class Menu {

    //This static variable stores the single instance of the Menu class
    private static Menu instance;

    //This list stores all products available in the menu
    private ArrayList<Product> products;

    //Private constructor to prevent creating Menu objects from outside this class
    private Menu() {
        products = new ArrayList<>();

        //Add products to the menu using ProductFactory
        products.add(ProductFactory.createProduct("Cheese Pizza"));
        products.add(ProductFactory.createProduct("Veggie Pizza"));
        products.add(ProductFactory.createProduct("Beef Burger"));
        products.add(ProductFactory.createProduct("Chicken Burger"));
        products.add(ProductFactory.createProduct("French Fries"));
        products.add(ProductFactory.createProduct("Mozzarella Sticks"));
        products.add(ProductFactory.createProduct("Apple Juice"));
        products.add(ProductFactory.createProduct("Orange Juice"));
        products.add(ProductFactory.createProduct("Water"));
        products.add(ProductFactory.createProduct("Pepsi"));
        products.add(ProductFactory.createProduct("7UP"));
    }

    //Returns the single shared menu instance
    public static Menu getInstance() {
        if (instance == null) {//if there is no menu object -> create one
            instance = new Menu();
        }
        return instance;
    }

    //Display all products in the menu with their category, name, and price
    public void displayMenu() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". "
                    + product.getCategory()
                    + " - " + product.getName()
                    + " - " + product.getPrice() + " SAR");
        }
    }

    //Returns the product based on its index in the menu
    public Product getProduct(int index) {
        if (index < 0 || index >= products.size()) {
            throw new IllegalArgumentException("Product number is not available in the menu");
        }
        return products.get(index);
    }

}