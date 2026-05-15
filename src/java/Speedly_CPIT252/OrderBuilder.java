import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();

    public OrderBuilder setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        this.customer = customer;
        return this;
    }

    public OrderBuilder addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        items.add(new OrderItem(product, quantity));
        return this;
    }

    private double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public Order build() {
        if (customer == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        return new Order(customer, items, calculateTotal());
    }
}