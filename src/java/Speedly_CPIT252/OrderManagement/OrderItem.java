package Speedly_CPIT252.OrderManagement;

import Speedly_CPIT252.Entity.Product;

public class OrderItem {

    //variables
    private Product product;
    private int quantity;

    //constructor
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    //calculate the price for this item
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    //get product
    public Product getProduct() {
        return product;
    }
}