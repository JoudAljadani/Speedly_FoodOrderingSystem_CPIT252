package Speedly_CPIT252.Entity;

public class Customer extends User {

    //calls the parent constructor to store the customer's information
    public Customer(String name, String phoneNumber, String email, String password) {
        super(name, phoneNumber, email, password);
    }

}