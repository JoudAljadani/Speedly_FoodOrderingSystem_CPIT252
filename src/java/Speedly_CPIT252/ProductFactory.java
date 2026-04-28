public class ProductFactory {

    public static Product createProduct(String name) {

        switch (name.toLowerCase()) {

            case "cheese pizza":
                return new Pizza("Cheese Pizza", 25);

            case "veggie pizza":
                return new Pizza("Veggie Pizza", 27);

            case "beef burger":
                return new Burger("Beef Burger", 22);

            case "chicken burger":
                return new Burger("Chicken Burger", 20);

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
