package modelTests;

import model.ChanceCardCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChanceCardCreatorTest {
    @Test
    void testCreateChanceCard() {
        ChanceCardCreator chanceCardCreator = new ChanceCardCreator();
        assertNotNull(chanceCardCreator.getChanceCard());
    }
}
