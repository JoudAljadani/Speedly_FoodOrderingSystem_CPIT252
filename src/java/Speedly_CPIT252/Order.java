package Speedly_CPIT252;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private String status;
    private double totalPrice;
    private PaymentStrategy paymentStrategy;

    private Order(Builder builder) {
        this.items = builder.items;
        this.status = "Pending";
        this.totalPrice = builder.calculateTotal();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        this.paymentStrategy = paymentStrategy;
    }

    public void payOrder() {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Please choose a payment method first");
        }

        paymentStrategy.pay(totalPrice);
        status = "Paid";
    }

    public static class Builder {
        private List<OrderItem> items = new ArrayList<>();

        public Builder addItem(Product product, int quantity) {
            if (product == null) {
                throw new IllegalArgumentException("Product cannot be null");
            }

            if (quantity <= 0) {
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
            if (items.isEmpty()) {
                throw new IllegalArgumentException("Order must have at least one item");
            }

            return new Order(this);
        }
    }
}