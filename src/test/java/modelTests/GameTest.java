package modelTests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private GameSettings gameSettings;
    private Game game;

    @BeforeEach
    void initGame() {
        gameSettings = new GameSettings();
        Player player = new Player(1,1500);
        Player player2 = new Player(2, 1500);
        gameSettings.getPlayers().add(player);
        gameSettings.getPlayers().add(player2);
        game = new Game(gameSettings);
        game.getDice().rollDice();
    }


    @Test
    void moveTest() {
        game.move(0);
        int previousPos = game.getCurrentPlayer().getPosition();
        Space previousSpace = game.getCurrentSpace();
        game.move(game.getDice().getTotalValue());
        assertNotEquals(previousPos, game.getCurrentPlayer().getPosition());
        assertNotEquals(previousSpace, game.getCurrentSpace());
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
}
