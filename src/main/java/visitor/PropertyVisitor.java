package visitor;

import model.Locale;
import model.Station;
import model.Utility;

/**
 * This method visits all property locales.
 * @author JonEmilsson
 */
public interface PropertyVisitor {
    void visit(Locale locale);
    void visit(Station station);
    void visit(Utility utility);
}
