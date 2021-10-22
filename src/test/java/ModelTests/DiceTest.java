package ModelTests;

import Model.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    private final Dice dice = new Dice();

    @BeforeEach
    void rollDice() {
        dice.rollDice();
    }

    @Test
    void diceRolledTests() {
        int o = dice.getDice1();
        int k = dice.getDice2();
        assertEquals(dice.getSum(), o + k);
    }
/*
    @Test
    void rolledDoublesTest() {
        while(!dice.isDoubles()) {
            dice.rollDice();
        }
        assertTrue(dice.isDoubles());
    }*/
}
