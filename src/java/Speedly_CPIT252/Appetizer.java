package Speedly_CPIT252;

public class Appetizer extends Product{
    public Appetizer(String name, double price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Appetizer";
    }
}
