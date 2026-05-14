public class Drink extends Product {

    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Drink";
    }
}