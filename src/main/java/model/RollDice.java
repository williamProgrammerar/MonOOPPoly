package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RollDice allows rolls of any amount of die with any amount of faces.
 *
 * @author williamProgrammerar
 */
public class RollDice {

    private static class Die {
        private static final Random rand = new Random();
        private final int faces;
        private int value;

        public Die(int faces) {
            this.faces = faces;
        }

        private void rollDie() {
            value = rand.nextInt(faces) + 1;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int i) {
            value = i;
        }
    }

    private static class Dice {
        private final List<Die> dices = new ArrayList<>();

        public Dice(int nrDice, int faces) {
            for (int i = 0; i < nrDice; i++) {
                dices.add(new Die(faces));
            }
        }

        public List<Die> getDices() {
            return dices;
        }
    }

    private final List<Die> dices;
    private final int faces;

    private boolean hasRolledDice;

    /**
     * RollDice constructor sets the amount of die that will be created.
     *
     * @param nrDice number of dice that will be used.
     * @param faces the amount of faces each die should have.
     */
    public RollDice(int nrDice, int faces) {
        Dice dice = new Dice(nrDice, faces);
        this.dices = dice.getDices();
        this.faces = faces;
        this.hasRolledDice = false;
    }

    public void rollDice() {
        if (!hasRolledDice) {
            for (Die die : dices) {
                die.rollDie();
            }
            hasRolledDice = true;
        }
    }

    public int getSpecificDieValue(int i) {
        return dices.get(i).getValue();
    }

    public void setSpecificDieValue(int i, int dieValue) {
        dices.get(i).setValue(dieValue);
    }

    public boolean hasRolledDoubles() {
        int initValue = dices.get(0).getValue();

        for (Die dice : dices) {
            if (dice.getValue() != initValue) {
                return false;
            }
        }

        return true;
    }

    public int getTotalValue() {
        int totalValue = 0;

        for (Die dice : dices) {
            totalValue += dice.getValue();
        }

        return totalValue;
    }

    public List<Die> getDices() {
        return dices;
    }

    public boolean isHasRolledDice() {
        return hasRolledDice;
    }

    public void setHasRolledDice(boolean hasRolledDice) {
        this.hasRolledDice = hasRolledDice;
    }
}
