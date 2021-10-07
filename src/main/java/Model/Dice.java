package Model;

import java.util.Random;

public class Dice {
    private static final Random rand = new Random();
    private int dice1;
    private int dice2;

    public int rollDice() {
        dice1 = rand.nextInt(6) + 1;
        dice2 = rand.nextInt(6) + 1;
        return getSum();
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

    public boolean bothDiceShowSix() {
        return dice1 + dice2 == 12;
    }
}


