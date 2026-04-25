package Speedly_CPIT252;

public class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    public void displayOrderItem() {
        System.out.println(item.getName() + " x" + quantity + " = " + getTotalPrice() + " SAR");
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}