
import java.util.ArrayList;

public class Menu {
    private static Menu instance;

    private ArrayList<Product> products;

    private Menu() {
        products = new ArrayList<>();

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

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void displayMenu() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". "
                    + product.getCategory()
                    + " - " + product.getName()
                    + " - " + product.getPrice() + " SAR");
        }
    }

    public Product getProduct(int index) {
        if (index < 0 || index >= products.size()) {
            throw new IllegalArgumentException("Product number is not available in the menu");
        }
        return products.get(index);
    }

}