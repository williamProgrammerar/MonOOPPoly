package modelTests;

import model.Locale;
import model.Section;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocaleTest {
    // Some tests are already covered by other test-classes
    Locale locale = new Locale("Test", 200, 100, new Section("Rainbow"), new int[] {10, 50, 150, 300, 500}, 50);
    @Test
    void sectionColourTest() {
        assertEquals(locale.getSectionColour(), "Rainbow");
    }

    @Test
    void testMaxHouses() {
        for (int i = 0; i < 10; i++) {
            locale.buildHouse();
        }
        assertEquals(5, locale.getAmountOfHouses());
        assertTrue(locale.hasMaxHouses());
    }

    @Test
    void testLocaleRent() {
        assertEquals(10, locale.getRent());

        locale.buildHouse();
        assertEquals(50, locale.getRent());

        locale.buildHouse();
        assertEquals(150, locale.getRent());

        locale.buildHouse();
        assertEquals(300, locale.getRent());

        locale.buildHouse();
        assertEquals(500, locale.getRent());
    }
}
