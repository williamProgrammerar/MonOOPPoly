package Model;

import java.util.ArrayList;
import java.util.List;

public class Section {
    public String getSectionColour() {
        return sectionColour;
    }

    String sectionColour;
    public Section( String sectionColour) {

        this.sectionColour = sectionColour;
    }


    public List<Locale> getLocaleList() {
        return localeList;
    }

    List<Locale> localeList = new ArrayList<>();
    int amountForMonopoly;

    public void addLocale(Locale locale){
        localeList.add(locale);
    }


}
