package model;

import visitor.HandleRentViewMapVisitor;

public class Utility extends Property {

    Dice dice = new Dice();
    private final int[] rent;
    private int nrOfUtilitiesOwned = 0;

    @Override
    public void accept(HandleRentViewMapVisitor visitor) {
        visitor.visit(this);
    }

    public Utility(String spaceName, int price, int mortgage, int[] rent) {
        super(spaceName, price, mortgage, rent);
        this.rent = rent;
    }

    public void setNrOfUtilitiesOwned(int nrOfUtilitiesOwned) {
        this.nrOfUtilitiesOwned = nrOfUtilitiesOwned - 1;
    }

    public int getRent() {
        dice.rollDice();
        System.out.println("Dice rolled a sum of " + dice.getSum());
        System.out.println("Rent is " + dice.getSum() * rent[nrOfUtilitiesOwned]);
        return dice.getSum() * rent[nrOfUtilitiesOwned];
    }
}
