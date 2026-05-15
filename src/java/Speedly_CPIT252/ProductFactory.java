
public class ProductFactory {

    public static Product createProduct(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Product name cannot be null");
        }

        switch (name.toLowerCase()) {

            case "cheese pizza":
                return new Pizza("Cheese Pizza", 25);

            case "veggie pizza":
                return new Pizza("Veggie Pizza", 27);

            case "beef burger":
                return new Burger("Beef Burger", 25);

            case "chicken burger":
                return new Burger("Chicken Burger", 20);

            case "french fries":
                return new Appetizer("French Fries", 10);

            case "mozzarella sticks":
                return new Appetizer("Mozzarella Sticks", 17);

            case "apple juice":
                return new Drink("Apple Juice", 8);

            case "orange juice":
                return new Drink("Orange Juice", 9);

            case "water":
                return new Drink("Water", 3);

            case "pepsi":
                return new Drink("Pepsi", 6);

            case "7up":
                return new Drink("7UP", 6);

            default:
                throw new IllegalArgumentException("Invalid product name");
        }
    }
}
