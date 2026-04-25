package Speedly_CPIT252;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void displayMenu() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". ");
            items.get(i).displayItem();
        }
    }

    public MenuItem getItem(int index) {
        return items.get(index);
    }
}