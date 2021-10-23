package modelTests;

import model.Player;
import model.Property;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private final Player player = new Player(1,1500);
    private Player player2;

    @Test
    void moveBackwardsPastGoTest() {
        player.move(-10);
        assertTrue(player.getPosition() > 0);
        assertFalse(player.HasPassedGo());
    }

    @Test
    void moveForwardsPastGoTest() {
        player.move(40);
        assertEquals(player.getPosition(), 0);
        assertTrue(player.HasPassedGo());
    }

    @Test
    void buyPropertyTest() {
        Property property = new Property("Test",200,100, new int[] {10,20,30,40,50,60});
        player.buyProperty(property);
        assertTrue(player.getProperties().contains(property));
        assertEquals(player.getCapital(), 1300);
        player.buyProperty(property);
        assertEquals(player.getProperties().size(), 1);

        Property property2 = new Property("Test2",1500,100, new int[] {10,20,30,40,50,60});
        player.buyProperty(property2);
        assertEquals(player.getCapital(), 1300);
        assertEquals(player.getProperties().size(), 1);
    }

    /*
    @Test
    void playerColourTest() {
        assertSame(player.getColor(), Color.DARKTURQUOISE);
    }*/

    @Test
    void playerStateTest() {
        player.setState("Human");
        assertSame("Human", player.getState());
    }

    @Test
    void wrongPlayerIdTest() {
        try {
            player2 = new Player(4, 1500);
        } catch (Exception e) {
            assertNull(player2);
        }
    }
}
