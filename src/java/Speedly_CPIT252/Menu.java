
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
       for (Product product : products) {
           System.out.println(product.getName() + "  -  "+ product.getPrice());
       }
    }

    /*public List<Product> getProducts() {
        return products;
    }*/

}