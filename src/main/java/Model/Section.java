package Model;

import java.util.ArrayList;
import java.util.List;

public class Section {
    public Section(int amountForMonopoly) {
        this.amountForMonopoly = amountForMonopoly;
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
