package modelTests;

import model.MoveChanceCard;
import model.Player;
import org.junit.jupiter.api.Test;

public class MoveChanceCardTest {
    @Test
    void testMoveChanceCard() {
        MoveChanceCard moveChanceCard = new MoveChanceCard("Text");
        Player player = new Player(1, 1500);
        moveChanceCard.doAction(player);
    }
}
