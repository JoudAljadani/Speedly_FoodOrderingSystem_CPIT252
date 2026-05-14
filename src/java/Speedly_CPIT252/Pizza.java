public class Pizza extends Product {

    public Pizza(String name, double price) {
        super(name, price);
    }

    @Override
    public String getCategory() {
        return "Pizza";
    }
}