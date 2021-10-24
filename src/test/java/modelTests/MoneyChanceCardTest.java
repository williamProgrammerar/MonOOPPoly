package modelTests;

import model.MoneyChanceCard;
import model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyChanceCardTest {
    @Test
    void testPositiveMoneyChanceCard() {
        MoneyChanceCard moneyChanceCard = new MoneyChanceCard("Text", 500);
        Player player = new Player(1, 1500);

        moneyChanceCard.doAction(player);
        assertEquals(player.getCapital(), 2000);
    }

    @Test
    void testNegativeMoneyChanceCard(){
        MoneyChanceCard moneyChanceCard = new MoneyChanceCard("Text", -500);
        Player player = new Player(1, 1500);

        moneyChanceCard.doAction(player);
        assertEquals(player.getCapital(), 1000);
    }
}
