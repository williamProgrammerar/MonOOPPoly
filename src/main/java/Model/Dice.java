package Model;

import java.util.Random;

/**
 * Dice is the class used to generate random integers one through six.
 * Supports throwing two different dices at once.
 *
 * @author williamProgrammerar
 */
public class Dice {
    private static final Random rand = new Random();
    private int dice1;
    private int dice2;

    public void rollDice() {
        dice1 = rand.nextInt(6) + 1;
        dice2 = rand.nextInt(6) + 1;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public int getSum() {
        return dice1 + dice2;
    }

    public boolean isDoubles() {
        return dice1 == dice2;
    }
}


