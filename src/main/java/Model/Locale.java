package Model;

public class Locale extends Property {

    private final int maxHouses = 5;
    private final int houseCost;
    private int nrOfHouses;
    Section section;
    private final int[] rent;
    public Locale(String spaceName, int price, int mortgage, Section section, int[] rent, int houseCost) {
        super(spaceName, price, mortgage, rent);
        this.houseCost = houseCost;
        this.section = section;
        this.nrOfHouses = 0;
        this.rent= rent;
        section.addLocale(this);
    }
    public int getRent() {
        return rent[nrOfHouses];
    }
    public Section getSection(){
        return section;
    }
}
