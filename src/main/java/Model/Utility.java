package Model;

public class Utility extends Property {

    Dice dice;

    public Utility(String spaceName, int price, int mortgage) {
        super(spaceName, price, mortgage);
    }

    public int getRent() {
        // dice.getSum() * number of utilities owned
        return dice.getSum();
    }
}
