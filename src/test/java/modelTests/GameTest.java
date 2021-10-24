package modelTests;

import model.*;
import observers.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;

    @BeforeEach
    void initGame() {
        GameSettings gameSettings = new GameSettings();
        Player player = new Player(1,1500);
        Player player2 = new Player(2, 1500);
        Player player3 = new Player(3, 1500);
        gameSettings.getPlayers().add(player);
        gameSettings.getPlayers().add(player2);
        gameSettings.getPlayers().add(player3);
        game = new Game(gameSettings);
        game.getDice().rollDice();
        Observer observer = (observable,Object) -> System.out.println("hej");
        game.attachObserver(observer);
    }

    @Test
    void nextPlayerTest() {
        Player prevPlayer = game.getPlayers().get(0);
        game.next();
        assertNotSame(prevPlayer, game.getPlayers().get(0));
    }

    @Test
    void moveToOwnedPropertyTest() {
        game.getPlayers().get(0).moveTo(0,false);
        int position = game.getBoard().findSpace("FORT NOX");
        Property property = (Property) game.getBoard().getSpace(position);
        property.setOwned(true);
        game.getPlayers().add(new Player(2,1500));
        property.setOwnerId(2);
        game.move(position);
        assertTrue(game.getPlayers().get(0).getCapital() < 1500);
    }

    @Test
    void moveToTaxSpaceTest() {
        game.getPlayers().get(0).moveTo(0,false);
        game.move(4);
        assertTrue(game.getCurrentPlayer().getCapital() < 1500);
    }

    @Test
    void moveToUSpaceTest() {
        game.getPlayers().get(0).moveTo(0,false);
        game.move(game.getBoard().findSpace("U"));
        assertEquals(game.getCurrentPlayer().getPosition(), 10);
    }

    @Test
    void movePastGo() {
        game.getCurrentPlayer().moveTo(39,true);
        assertFalse(game.getCurrentPlayer().HasPassedGo());

        game.move(10);
        assertTrue(game.getCurrentPlayer().HasPassedGo());
    }


    @Test
    void testJailTurn() {

    }

    @Test
    void testCheckBankruptcy() {
        Player player3 = new Player(3, 1500);
        game.getPlayers().add(player3);

        assertFalse(game.getPlayers().get(0).isBankrupt());
        game.getPlayers().get(0).setCapital(0);
        for (int i = 0; i < game.getPlayers().size(); i++) {
            game.getDice().rollDice();
            game.endTurn();
        }
        assertTrue(game.getPlayers().get(game.getPlayers().size() - 1).isBankrupt());
    }

    @Test
    void testBuyHouse() {
        Locale locale1 = (Locale) game.getBoard().getSpace(game.getBoard().findSpace("WINDEN"));
        Locale locale2 = (Locale) game.getBoard().getSpace(game.getBoard().findSpace("XP-LABBET"));
        Locale locale3 = (Locale) game.getBoard().getSpace(game.getBoard().findSpace("BULTEN"));

        game.buyHouse(locale1);
        assertEquals(locale1.getAmountOfHouses(), 0);

        game.getCurrentPlayer().getProperties().add(locale1);
        game.getCurrentPlayer().getProperties().add(locale2);
        game.getCurrentPlayer().getProperties().add(locale3);

        game.buyHouse(locale1);
        assertEquals(locale1.getAmountOfHouses(), 1);
    }

    @Test
    void testNextBankrupt() {
       game.getPlayers().get(1).setBankrupt();

       game.next();

       assertSame(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    void testSelectSpace() {
        game.move(5);
        game.setSelectedSpace(game.getCurrentSpace());
        Space selectedSpace = game.getSelectedSpace();
        game.setSelectedSpace(selectedSpace);
        assertSame(game.getCurrentSpace(), game.getSelectedSpace());
    }

    @Test
    void getPlayerUsingIDTest() throws Exception {
        Player playerTest = game.getPlayerUsingID(1);
        assertSame(playerTest, game.getCurrentPlayer());
        try {
            Player playerTest2 = game.getPlayerUsingID(5);
        } catch (Exception e) {
            System.out.println("Player doesn't exist");
        }
    }
}
