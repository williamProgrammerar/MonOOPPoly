package View;

import Model.Locale;
import Model.Property;
import Model.Station;
import Model.Utility;
import Visitor.HandleRentViewMap;

import java.util.HashMap;

public class RentViewManager {

    public HashMap<String,PropertyRentView> propertyRentViewHashMap = new HashMap<>();

    private HandleRentViewMap handleRentViewMap = new HandleRentViewMap(this);

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
