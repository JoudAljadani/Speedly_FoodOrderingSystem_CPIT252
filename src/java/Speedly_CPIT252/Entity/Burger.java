package Speedly_CPIT252.Entity;

public class Burger extends Product {

    //calls the parent product constructor to set the burger name and price
    public Burger(String name, double price) {
        super(name, price);
    }

    //returns the category of this product
    @Override
    public String getCategory() {
        return "Burger";
    }
}