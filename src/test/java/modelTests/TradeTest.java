package modelTests;


import model.*;
import model.Player;
import model.Property;
import model.Trade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TradeTest {

    private final Trade trade = new Trade();
    private final Player playerA = new Player(1,1000);
    private final Player playerB = new Player(2, 1000);
    private final Property propertyA = new Locale("A",200,100,new Section("Rainbow"), new int[] {1,2,3,4,5},20);
    private final Property propertyB = new Locale("A",200,100,new Section("Rainbow"), new int[] {1,2,3,4,5},20);
    private final Property propertyC = new Locale("A",200,100,new Section("Rainbow"), new int[] {1,2,3,4,5},20);

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
        testIfNoCurrencyOffers();
        testAddCurrencyOffer();
        testChangeCurrencyOffer();
        testAddMultipleCurrencyOffers();
        testAddMoreMoneyThanOwned();
        testAddInvalidCurrencyOffer();
    }

    void testIfNoCurrencyOffers() {
        assertEquals(trade.getCurrencyOffers().size(), 0);
    }

    void testAddCurrencyOffer() {
        trade.addCurrencyToTrade(playerA,500);
        assertEquals(trade.getCurrencyOffers().get(playerA), 500);
    }

    void testChangeCurrencyOffer() {
        trade.addCurrencyToTrade(playerA,200);
        assertEquals(trade.getCurrencyOffers().get(playerA), 200);
    }

    void testAddMultipleCurrencyOffers() {
        trade.addCurrencyToTrade(playerB, 700);
        assertEquals(trade.getCurrencyOffers().get(playerB), 700);
        assertEquals(trade.getCurrencyOffers().size(), 2);
    }

    void testAddMoreMoneyThanOwned() {
        trade.addCurrencyToTrade(playerB, 1500);
        assertEquals(trade.getCurrencyOffers().get(playerB), 700);
    }

    void testAddInvalidCurrencyOffer() {
        trade.addCurrencyToTrade(playerA,-100);
        assertEquals(trade.getCurrencyOffers().get(playerA), 200);
    }

    @Test
    void addPropertyToTradeTest() {
        addProperties();
        assertEquals(trade.getPropertyOffers().size(), 0);

        testAddInvalidProperty();
        testAddPropertyToTrade();
        testAddMoreProperty();
        testAddSameProperty();
    }

    void addProperties() {
        playerA.getProperties().add(propertyA);
        playerA.getProperties().add(propertyB);

        playerB.getProperties().add(propertyC);
    }

    void testAddInvalidProperty() {
        trade.addPropertyToTrade(playerA, propertyC);
        assertEquals(trade.getPropertyOffers().size(), 0);
    }

    void testAddPropertyToTrade() {
        trade.addPropertyToTrade(playerA, propertyA);
        assertEquals(trade.getPropertyOffers().size(), 1);
    }

    void testAddMoreProperty() {
        // There should be two properties in playerA's offer
        trade.addPropertyToTrade(playerA, propertyB);
        assertEquals(trade.getPropertyOffers().get(playerA).size(), 2);
    }

    void testAddSameProperty() {
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
        firstTradePropertyTest();
        secondTradePropertyTest();
    }

    void firstTradePropertyTest() {
        playerA.getProperties().add(propertyA);
        playerB.getProperties().add(propertyB);
        trade.startTrade(playerA,playerB);
        trade.addPropertyToTrade(playerA, propertyA);
        trade.acceptTrade();
        assertTrue(playerA.getProperties().isEmpty());
        assertEquals(playerB.getProperties().size(), 2);
    }

    void secondTradePropertyTest() {
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
