package Speedly_CPIT252;

import Speedly_CPIT252.Entity.*;
import Speedly_CPIT252.Factory.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//This class implements the testing of factory method for user types
public class UserFactoryTest {

    @Test
    void shouldCreateCustomer() {
        //create a customer object using UserFactory
        User user = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        //Check that the created user is a customer and has the correct data
        assertTrue(user instanceof Customer);
        assertEquals("Joud", user.getName());
        assertEquals("0512345678", user.getPhoneNumber());
        assertEquals("Joud@gmail.com", user.getEmail());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void shouldCreateEmployee() {
        //create an employee object using UserFactory
        User user = UserFactory.createUser("staff", "Speedly Staff",
                "0500000000", "staff@speedly.com", "staff123");

        //Check that the created user is an employee and has the correct data
        assertTrue(user instanceof Staff);
        assertEquals("Speedly Staff", user.getName());
        assertEquals("0500000000", user.getPhoneNumber());
        assertEquals("staff@speedly.com", user.getEmail());
        assertEquals("staff123", user.getPassword());
    }

    @Test
    void invalidUserTypeShouldThrowException() {
        //check that an invalid user type throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser("admin", "Leena", "0511111111",
                    "Leena@gmail.com", "4321");});
    }

    @Test
    void nullUserTypeShouldThrowException() {
        //check that a null user type throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser(null, "Leena", "0511111111",
                    "Leena@gmail.com", "4321");});
    }
}