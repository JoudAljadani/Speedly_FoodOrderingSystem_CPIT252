package Speedly_CPIT252;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    void shouldCreateCustomer() {
        User user = UserFactory.createUser("customer", "Joud",
                "0512345678", "Joud@gmail.com", "1234");

        assertTrue(user instanceof Customer);
        assertEquals("Joud", user.getName());
        assertEquals("0512345678", user.getPhoneNumber());
        assertEquals("Joud@gmail.com", user.getEmail());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void shouldCreateEmployee() {
        User user = UserFactory.createUser("employee", "Speedly Staff",
                "0500000000", "staff@speedly.com", "staff123");

        assertTrue(user instanceof Employee);
        assertEquals("Speedly Staff", user.getName());
        assertEquals("0500000000", user.getPhoneNumber());
        assertEquals("staff@speedly.com", user.getEmail());
        assertEquals("staff123", user.getPassword());
    }

    @Test
    void invalidUserTypeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser("admin", "Leena", "0511111111",
                    "Leena@gmail.com", "4321");});
    }

    @Test
    void nullUserTypeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserFactory.createUser(null, "Leena", "0511111111",
                    "Leena@gmail.com", "4321");});
    }
}