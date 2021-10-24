package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JonEmilsson
 */
public class Section {
    public String getSectionColour() {
        return sectionColour;
    }

    private final String sectionColour;

    public Section( String sectionColour) {
        this.sectionColour = sectionColour;
    }

    public List<Locale> getLocaleList() {
        return localeList;
    }

    private final List<Locale> localeList = new ArrayList<>();

    /**
     * This adds a locale to the current section, so that section can keep track how many locales are neccary for
     * monopoly
     * @param locale this parameter is the locale being added.
     */
    public void addLocale(Locale locale){
        localeList.add(locale);
    }
}
