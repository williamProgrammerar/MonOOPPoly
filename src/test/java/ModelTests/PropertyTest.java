package ModelTests;

import Model.Property;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {
    private final Property property = new Property("Test", 200, 100, new int[] {1,2,3,4,5,6});

    @Test
    void mortgageTest() {
        property.setMortgaged(true);
        assertTrue(property.isMortgaged());
        assertEquals(property.getMortgage(), 100);
    }

    @Test
    void getRentTest() {
        assertEquals(property.getRent(), 1);
    }
}
