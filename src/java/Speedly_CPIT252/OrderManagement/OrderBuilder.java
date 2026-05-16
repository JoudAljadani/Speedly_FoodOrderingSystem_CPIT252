package Speedly_CPIT252.OrderManagement;

import Speedly_CPIT252.Entity.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private Customer customer;

    //list to store all order items
    private List<OrderItem> items = new ArrayList<>();

    //assign customer to the order
    public OrderBuilder setCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        this.customer = customer;

        return this;  //return builder object for method chaining
    }

    //add product with quantity to the order
    public OrderBuilder addItem(Product product, int quantity) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        //prevent invalid quantity
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        //create and add order item
        items.add(new OrderItem(product, quantity));

        return this; //return builder object for method chaining
    }

    //calculate total order price
    private double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    //build final order object
    public Order build() {
        //check if customer was assigned
        if (customer == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }

        //prevent creating empty order
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        //create order with calculated total
        return new Order(customer, items, calculateTotal());
    }
}