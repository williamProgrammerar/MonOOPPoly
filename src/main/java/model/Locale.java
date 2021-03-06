package model;


import visitor.HandleRentViewMapVisitor;

import java.util.ArrayList;
import java.util.List;

public class Locale extends Property {

    private final int maxHouses = 5;
    private final int houseCost;

    public String getSectionColour() {
        return sectionColour;
    }

    private final String sectionColour;

    public void buildHouse() {
        if (houses.size() < maxHouses) {
            houses.add(new House());
        }
        else {
            System.out.println("Maximum of houses reached");
            throw new IllegalArgumentException();
        }
    }

    private final List<House> houses = new ArrayList<>();

    private final Section section;
    
    private final int[] rent;

    public Locale(String spaceName, int price, int mortgage, Section section, int[] rent, int houseCost) {
        super(spaceName, price, mortgage, rent);
        this.houseCost = houseCost;
        this.section = section;
        this.rent= rent;
        this.sectionColour = section.getSectionColour();
        section.addLocale(this);
    }


    @Override
    public void accept(HandleRentViewMapVisitor visitor) {
        visitor.visit(this);
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

    public boolean hasMaxHouses() {
        return !(houses.size() < maxHouses);
    }

    public int getAmountOfHouses() {
        return houses.size();
    }
}
