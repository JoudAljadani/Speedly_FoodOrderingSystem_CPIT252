package Speedly_CPIT252.Entity;

public abstract class Product {

    //Attributes
    private String name;
    private double price;

    //Constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //Getters
    public String getName() {
            return name;
        }

    public double getPrice() {
            return price;
        }

    //Abstract method that each subclass must implement to return its category
    public abstract String getCategory();
}

