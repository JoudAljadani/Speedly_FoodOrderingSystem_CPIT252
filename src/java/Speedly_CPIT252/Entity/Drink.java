package Speedly_CPIT252.Entity;

public class Drink extends Product {

    //calls the parent product constructor to set the drink name and price
    public Drink(String name, double price) {
        super(name, price);
    }

    //returns the category of this product
    @Override
    public String getCategory() {
        return "Drink";
    }
}