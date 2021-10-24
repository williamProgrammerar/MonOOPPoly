package modelTests;

import model.Property;
import model.PropertyItem;
import org.junit.jupiter.api.Test;
import visitor.HandleRentViewMapVisitor;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyItemTest {
    @Test
    void testPropertyItem() {
        Property property = new Property("Test", 200, 100, new int[] {1,2,3,4,5}) {
            @Override
            public void accept(HandleRentViewMapVisitor visitor) {

            }
        };
        PropertyItem propertyItem = new PropertyItem(property);
        assertSame(propertyItem.getProperty(), property);
        assertSame(propertyItem.getPropertyName(), property.getSpaceName());
    }
}
