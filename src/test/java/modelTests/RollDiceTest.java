package modelTests;

import model.RollDice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RollDiceTest {

    @Test
    void testRollOneDice() {
        RollDice dice = new RollDice(1,6);

        assertEquals(dice.getDices().size(), 1);
        for (int i = 0; i < 100; i++) {
            dice.rollDice();
            int totalValue = dice.getTotalValue();
            assertTrue(totalValue >= 1 && totalValue <= 6);
        }
    }

    @Test
    void testRollTwoDice() {
        RollDice dice = new RollDice(2,6);

        assertEquals(dice.getDices().size(), 2);
        for (int i = 0; i < 100; i++) {
            dice.rollDice();
            int totalValue = dice.getTotalValue();
            assertTrue(totalValue >= 2 && totalValue <= 12);
        }
    }

    @Test
    void testRollDoubles() {
        RollDice dice = new RollDice(2,6);

        assertFalse(dice.hasRolledDoubles());

        for (int i = 0; i < dice.getDices().size(); i++) {
            dice.setSpecificDieValue(i,6);
        }

        assertTrue(dice.hasRolledDoubles());
    }

    @Test
    void getSpecificValueTest() {
        RollDice dice = new RollDice(1,6);

        dice.rollDice();

        int value = dice.getTotalValue();

        assertEquals(value, dice.getSpecificDieValue(0));
    }
}
