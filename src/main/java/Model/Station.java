package Model;

public class Station extends Property {

    private final int rent = 25;

    public Station(String spaceName, int price, int mortgage) {
        super(spaceName, price, mortgage);
    }

    public int getRent() {
        // rent * 2 ^ (number of stations owned - 1)
        return rent;
    }
}
