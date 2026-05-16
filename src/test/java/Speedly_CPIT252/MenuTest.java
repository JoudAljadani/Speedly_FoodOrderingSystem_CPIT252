package Speedly_CPIT252;


import Speedly_CPIT252.Entity.Menu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//This class implements the testing of Singleton design pattern
public class MenuTest {

    @Test
    void menuShouldReturnSameInstance() {
        //Get the menu object twice
        Menu menu1 = Menu.getInstance();
        Menu menu2 = Menu.getInstance();

        //Check if both objects point to the same menu object
        assertSame(menu1, menu2);
    }
}
