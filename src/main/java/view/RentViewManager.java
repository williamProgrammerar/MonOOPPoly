package view;

import model.Locale;
import model.Property;
import model.Station;
import model.Utility;
import visitor.HandleRentViewMapVisitor;

import java.util.HashMap;

/**
 * This class makes it so that you don't you have to specify which class is being put into the propertyRentViewHashMap
 * @author JonEmilsson
 */
public class RentViewManager {

    public HashMap<String,PropertyRentView> propertyRentViewHashMap = new HashMap<>();

    private final HandleRentViewMapVisitor handleRentViewMap = new HandleRentViewMapVisitor(this);

    public void add(Property property){
        property.accept(handleRentViewMap);
    }
    public void add(Locale locale){
        propertyRentViewHashMap.put(locale.getSpaceName(),new LocaleRentView(locale));
    }
    public void add(Station station){
        propertyRentViewHashMap.put(station.getSpaceName(),new StationRentView(station));
    }
    public void add(Utility utility){
        propertyRentViewHashMap.put(utility.getSpaceName(),new UtilityRentView(utility));
    }

    public HashMap<String, PropertyRentView> getPropertyRentViewHashMap() {
        return propertyRentViewHashMap;
    }
}
