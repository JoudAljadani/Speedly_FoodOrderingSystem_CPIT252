
public class Customer extends User {

    public Customer(String name, String phoneNumber, String email) {
        super(name, phoneNumber, email);
    }

    public void viewMenu() {
        Menu menu = Menu.getInstance();
        menu.displayMenu();
    }
}
