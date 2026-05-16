package Speedly_CPIT252;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void menuShouldReturnSameInstance() {
        Menu menu1 = Menu.getInstance();
        Menu menu2 = Menu.getInstance();

        assertSame(menu1, menu2);
    }
}
