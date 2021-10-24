package model;

import visitor.HandleRentViewMapVisitor;

public class Utility extends Property {
    private final RollDice dice;

    private final int[] rent;

    private int nrOfUtilitiesOwned = 0;

    @Override
    public void accept(HandleRentViewMapVisitor visitor) {
        visitor.visit(this);
    }

    public Utility(String spaceName, int price, int mortgage, int[] rent, RollDice rollDice) {
        super(spaceName, price, mortgage, rent);
        this.rent = rent;
        this.dice = rollDice;
    }

    public void setNrOfUtilitiesOwned(int nrOfUtilitiesOwned) {
        this.nrOfUtilitiesOwned = nrOfUtilitiesOwned - 1;
    }

    public int getRent() {
        System.out.println("Dice rolled a sum of " + dice.getTotalValue());
        System.out.println("Rent is " + dice.getTotalValue() * rent[nrOfUtilitiesOwned]);
        return dice.getTotalValue() * rent[nrOfUtilitiesOwned];
    }
}
