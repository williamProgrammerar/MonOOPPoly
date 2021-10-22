package Model;

/**
 * PropertyItem is used as items for the property comboBoxes in the TradeController class.
 *
 * @author williamProgrammerar
 */
public class PropertyItem {
    private final String propertyName;
    private final Property property;

    public PropertyItem(Property property) {
        this.propertyName = property.getSpaceName();
        this.property = property;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Property getProperty() {
        return property;
    }
}
