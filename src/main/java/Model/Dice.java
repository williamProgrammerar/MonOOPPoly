package Model;

import java.util.Random;

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

    public boolean bothDiceShowSix() {
        return dice1 + dice2 == 12;
    }

    public boolean doubles() {
        return dice1 == dice2;
    }
}


