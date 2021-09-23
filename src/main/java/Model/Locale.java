package Model;

public class Locale extends Property {

    private final int maxHouses = 5;
    private final String sectionColour;
    private final int[] rent;
    private final int houseCost;
    private int nrOfHouses;


    public Locale(String spaceName, int price, int mortgage, String sectionColour, int[] rent, int houseCost) {
        super(spaceName, price, mortgage);
        this.sectionColour = sectionColour;
        this.rent = rent;
        this.houseCost = houseCost;
        this.nrOfHouses = 0;
    }

    public String getSectionColour() {
        return sectionColour;
    }

    public int getRent() {
        return rent[nrOfHouses];
    }
}
