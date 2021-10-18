package Model;

import java.util.ArrayList;
import java.util.List;

public class Locale extends Property {

    private final int maxHouses = 5;
    private final int houseCost;

    public void buildHouse(){
        houses.add(new House());
    }
    private final List<House> houses = new ArrayList<>();
    Section section;
    
    private final int[] rent;
    public Locale(String spaceName, int price, int mortgage, Section section, int[] rent, int houseCost) {
        super(spaceName, price, mortgage, rent);
        this.houseCost = houseCost;
        this.section = section;
        this.rent= rent;

        section.addLocale(this);
    }
    public int getRent() {
        return rent[houses.size()];
    }
    public Section getSection(){
        return section;
    }

    public int getHouseCost() {
        return houseCost;
    }
}
