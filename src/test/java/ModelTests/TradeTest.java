package ModelTests;

import Model.Player;
import Model.Property;
import Model.Trade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TradeTest {

    private final Trade trade = new Trade();
    private final Player playerA = new Player(1,1000);
    private final Player playerB = new Player(2, 1000);
    private final Property propertyA = new Property("A",200,100, new int[] {1,2,3,4,5});
    private final Property propertyB = new Property("B",200,100, new int[] {1,2,3,4,5});
    private final Property propertyC = new Property("C",200,100, new int[] {1,2,3,4,5});

    @Test
    void startTradeTest() {
        trade.setTradeActive(false);
        trade.startTrade(playerA, playerB);
        assertTrue(trade.isTradeActive());
        assertEquals(trade.getPlayers().length, 2);
    }

    @Test
    void cancelTradeTest() {
        trade.startTrade(playerA, playerB);
        trade.closeTrade();
        assertFalse(trade.isTradeActive());
        assertEquals(trade.getPlayers().length, 0);
    }

    @Test
    void addCurrencyToTradeTest() {
        // Make sure there is no currency in trade
        assertEquals(trade.getCurrencyOffers().size(), 0);

        // playerA adds 500kr to the trade
        trade.addCurrencyToTrade(playerA,500);
        assertEquals(trade.getCurrencyOffers().get(playerA), 500);

        // playerA changes their offer to 200kr from 500kr.
        trade.addCurrencyToTrade(playerA,200);
        assertEquals(trade.getCurrencyOffers().get(playerA), 200);

        // PlayerB adds 700kr to trade.
        // There should be two currency offers in trade.
        trade.addCurrencyToTrade(playerB, 700);
        assertEquals(trade.getCurrencyOffers().get(playerB), 700);
        assertEquals(trade.getCurrencyOffers().size(), 2);

        // playerB attempts to add more money than they currently have. Their offer should remain as 700kr.
        trade.addCurrencyToTrade(playerB, 1500);
        assertEquals(trade.getCurrencyOffers().get(playerB), 700);

        // playerA attempts to add an invalid value to the trade. Their offer should remain as 200kr.
        trade.addCurrencyToTrade(playerA,-100);
        assertEquals(trade.getCurrencyOffers().get(playerA), 200);
    }

    @Test
    void addPropertyToTradeTest() {
        playerA.getProperties().add(propertyA);
        playerA.getProperties().add(propertyB);

        playerB.getProperties().add(propertyC);

        assertEquals(trade.getPropertyOffers().size(), 0);

        // playerA does not own propertyC, so there should be no offers
        trade.addPropertyToTrade(playerA, propertyC);
        assertEquals(trade.getPropertyOffers().size(), 0);

        trade.addPropertyToTrade(playerA, propertyA);
        assertEquals(trade.getPropertyOffers().size(), 1);

        // There should be two properties in playerA's offer
        trade.addPropertyToTrade(playerA, propertyB);
        assertEquals(trade.getPropertyOffers().get(playerA).size(), 2);

        trade.addPropertyToTrade(playerA, propertyB);
        assertEquals(trade.getPropertyOffers().get(playerA).size(), 2);
    }

    @Test
    void testTradingCurrency() {
        assertEquals(playerB.getCapital(), playerA.getCapital());
        trade.startTrade(playerA,playerB);
        trade.addCurrencyToTrade(playerA,100);
        trade.acceptTrade();
        assertTrue(playerA.getCapital() < playerB.getCapital());

        trade.startTrade(playerA,playerB);
        trade.addCurrencyToTrade(playerB,100);
        trade.acceptTrade();
        assertEquals(playerB.getCapital(), playerA.getCapital());
    }

    @Test
    void testTradingProperties() {
        playerA.getProperties().add(propertyA);
        playerB.getProperties().add(propertyB);
        trade.startTrade(playerA,playerB);
        trade.addPropertyToTrade(playerA, propertyA);
        trade.acceptTrade();
        assertTrue(playerA.getProperties().isEmpty());
        assertEquals(playerB.getProperties().size(), 2);

        playerA.getProperties().add(propertyC);
        trade.startTrade(playerA,playerB);
        trade.addPropertyToTrade(playerA, propertyC);
        trade.addPropertyToTrade(playerB, propertyA);
        trade.addPropertyToTrade(playerB, propertyB);
        trade.acceptTrade();
        assertTrue(playerA.getProperties().contains(propertyA) && playerA.getProperties().contains(propertyB));
        assertEquals(playerA.getProperties().size(), 2);
        assertTrue(playerB.getProperties().contains(propertyC));
        assertEquals(playerB.getProperties().size(), 1);
    }
}
