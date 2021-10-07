package Model;

public class Utility extends Property {

    Dice dice;
    private final int[] rent;

    public Utility(String spaceName, int price, int mortgage, int[] rent,Section section) {
        super(spaceName, price, mortgage, rent, section);
        this.rent = rent;
    }

    public int getRent() {
        // dice.getSum() * number of utilities owned
        return dice.getSum();
    }
}
