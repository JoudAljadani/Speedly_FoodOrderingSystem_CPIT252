package Speedly_CPIT252;

abstract public class MenuItem {
    protected String name;
    protected double price;
    private String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void displayItem() {
        System.out.println(name + " - " + price + " SAR - " + category);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}

