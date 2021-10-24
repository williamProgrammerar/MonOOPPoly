package modelTests;

import model.Player;
import model.PlayerItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerItemTest {
    @Test
    void testPlayerItem() {
        Player player = new Player(1, 1500);
        PlayerItem playerItem = new PlayerItem(player);

        assertSame(playerItem.getPlayer(), player);
        assertSame(playerItem.getPlayerName(), player.getName());
    }
}
