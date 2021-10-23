package Visitor;

import Model.Locale;
import Model.Space;
import Model.Station;
import Model.Utility;
import View.*;

import java.util.HashMap;

public class HandleRentViewMap implements SomebodyVisitor {
    private final RentViewManager rentViewManager;
    public HandleRentViewMap(RentViewManager rentViewManager) {
        this.rentViewManager = rentViewManager;
    }
    public void visit(Locale locale){
        rentViewManager.add(locale);
    }
    public void visit(Station station){
        rentViewManager.add(station);

    }
    public void visit(Utility utility){
        rentViewManager.add(utility);
    }


}
