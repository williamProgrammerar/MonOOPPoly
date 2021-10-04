package Model;

public class Utility extends Property {

    Dice dice;
    private final int[] rent;

    public Utility(String spaceName, int price, int mortgage, int[] rent) {
        super(spaceName, price, mortgage, rent);
        this.rent = rent;
    }

    public int getRent() {
        // dice.getSum() * number of utilities owned
        return dice.getSum();
    }
}
