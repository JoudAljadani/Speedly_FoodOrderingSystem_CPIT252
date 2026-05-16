package Speedly_CPIT252.Entity;

public class Appetizer extends Product{

    //calls the parent product constructor to set the appetizer name and price
    public Appetizer(String name, double price) {
        super(name, price);
    }

    //returns the category of this product
    @Override
    public String getCategory() {
        return "Appetizer";
    }
}
