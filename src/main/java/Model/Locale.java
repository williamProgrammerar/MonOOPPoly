package Model;

public class Locale extends Property {

    private final int maxHouses = 5;
    private final String sectionColour;
    private final int houseCost;
    private int nrOfHouses;
    private final int[] rent;

    public Locale(String spaceName, int price, int mortgage, String sectionColour, int[] rent, int houseCost) {
        super(spaceName, price, mortgage, rent);
        this.sectionColour = sectionColour;
        this.houseCost = houseCost;
        this.nrOfHouses = 0;
        this.rent= rent;
    }

    public String getSectionColour() {
        return sectionColour;
    }

    public int getRent() {
        return rent[nrOfHouses];
    }

    public int getHouseCost() {
        return houseCost;
    }
}
