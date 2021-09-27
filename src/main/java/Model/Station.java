package Model;

public class Station extends Property {

    private final int rent = 25;

    public Station(String spaceName, int price, int mortgage) {
        super(spaceName, price, mortgage);
    }

    public int getRent() {
        // rent * number of stations owned
        return rent;
    }
}
