package Model;

public class Station extends Property {

    private final int[] rent;
    private int nrOfStationsOwned = 0;
    public Station(String spaceName, int price, int mortgage, int[] rent) {
        super(spaceName, price, mortgage, rent);
        this.rent = rent;
    }

    public int getRent() {
        //TODO fix so this checks amount of owned staions
        return rent[nrOfStationsOwned];
    }
}
