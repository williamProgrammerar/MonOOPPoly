package visitor;

import model.Locale;
import model.Station;
import model.Utility;

public interface SomebodyVisitor {
    void visit(Locale locale);
    void visit(Station station);
    void visit(Utility utility);
}
