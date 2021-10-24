package modelTests;

import model.Utility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    private final Utility utility = new Utility("Utility", 200, 100, new int[] {4, 10});

    @Test
    void utilityRentTest() {
        for (int i = 0; i < 36; i++) {
            assertTrue(utility.getRent() >= 8);
        }
        utility.setNrOfUtilitiesOwned(2);
        for (int i = 0; i < 36; i++) {
            assertTrue(utility.getRent() >= 20);
        }
    }
}
