package Speedly_CPIT252.Factory;

import Speedly_CPIT252.Entity.*;

//This class implements Factory method design pattern
public class UserFactory {

    //This method creates and returns the correct user object based on the user type
    public static User createUser(String type, String name, String phoneNumber, String email, String password) {

        if (type == null) {
            throw new IllegalArgumentException("User type cannot be null");
        }

        //create the correct user object based on the given type
        switch (type.toLowerCase()) {
            case "customer":
                return new Customer(name, phoneNumber, email, password);

            case "staff":
                return new Staff(name, phoneNumber, email, password);

            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}