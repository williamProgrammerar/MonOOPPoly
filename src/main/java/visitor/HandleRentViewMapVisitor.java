package visitor;

import model.Locale;
import model.Station;
import model.Utility;
import view.*;

/**
 * This class visits locales and adds them to rentViewManager
 * @author JonEmilsson
 */
public class HandleRentViewMapVisitor implements PropertyVisitor {
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
