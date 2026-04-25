package Speedly_CPIT252;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<OrderItem> items;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    public double calculateTotal() {
        double total = 0;

        for (OrderItem orderItem : items) {
            total += orderItem.getTotalPrice();
        }

        return total;
    }

    public void displayOrder() {
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Items:");

        for (OrderItem orderItem : items) {
            orderItem.displayOrderItem();
        }

        System.out.println("Total: " + calculateTotal() + " SAR");
    }
}