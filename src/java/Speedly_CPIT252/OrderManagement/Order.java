package Speedly_CPIT252.OrderManagement;

import Speedly_CPIT252.Entity.Customer;
import Speedly_CPIT252.State.*;
import Speedly_CPIT252.Observer.OrderObserver;
import Speedly_CPIT252.Strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;

    private static int counter = 1;
    private int orderId;

    private List<OrderItem> items;
    private OrderState state;
    private double totalPrice;
    private PaymentStrategy paymentStrategy;

    private List<OrderObserver> observers = new ArrayList<>();

    Order(Customer customer, List<OrderItem> items, double totalPrice) {
        this.orderId = counter++;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
        this.state = new CreatedState();
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getStatus() {
        return state.getStatusName();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update();
        }
    }

    public void setState(OrderState state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }

        this.state = state;
        notifyObservers();
    }

    public void nextState() {
        state.next(this);
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

        if (!getStatus().equals("Created")) {
            throw new IllegalStateException("Order can only be paid from Created status");
        }

        paymentStrategy.pay(totalPrice);
        nextState();
    }
}