package Visitor;

import Model.Locale;
import Model.Station;
import Model.Utility;

public interface SomebodyVisitor {
    void visit(Locale locale);
    void visit(Station station);
    void visit(Utility utility);
}
