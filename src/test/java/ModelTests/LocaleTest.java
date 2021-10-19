package ModelTests;

import Model.Locale;
import Model.Section;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocaleTest {
    // Some tests are already covered by other test-classes
    Locale locale = new Locale("Test", 200, 100, new Section("Rainbow"), new int[] {10, 50, 150, 300, 500}, 50);
    @Test
    void sectionColourTest() {
        assertEquals(locale.getSectionColour(), "Rainbow");
    }
}
