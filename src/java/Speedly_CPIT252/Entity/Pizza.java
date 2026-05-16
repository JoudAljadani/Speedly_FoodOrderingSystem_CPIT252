package Speedly_CPIT252.Entity;

public class Pizza extends Product {

    //calls the parent product constructor to set the pizza name and price
    public Pizza(String name, double price) {
        super(name, price);
    }

    //returns the category of this product
    @Override
    public String getCategory() {
        return "Pizza";
    }
}