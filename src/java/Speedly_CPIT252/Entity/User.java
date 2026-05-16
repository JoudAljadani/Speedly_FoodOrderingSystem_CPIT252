package Speedly_CPIT252.Entity;

public abstract class User {

    //Attributes
    private static int counter = 1;
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;

    //Constructors
    public User(String name, String phoneNumber, String email, String password) {
        this.id = counter++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString() to print each user information
    @Override
    public String toString() {
        return "User ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}