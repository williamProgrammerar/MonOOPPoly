package visitor;

import model.Locale;
import model.Station;
import model.Utility;
import view.*;

public class HandleRentViewMapVisitor implements SomebodyVisitor {
    private final RentViewManager rentViewManager;
    public HandleRentViewMapVisitor(RentViewManager rentViewManager) {
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
