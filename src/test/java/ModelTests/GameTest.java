package ModelTests;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private final GameSettings gameSettings = new GameSettings();
    private Game game = new Game(gameSettings);

    @BeforeEach
    void initPlayer() {
        Player player = new Player(1,1500);
        game.getPlayers().add(player);
    }

    @BeforeEach
    void rollDice() {
        game.getDice().rollDice();
    }

    /*
    @Test
    void moveTest() {
        game.move(0);
        int previousPos = game.getCurrentPlayer().getPosition();
        Space previousSpace = game.getCurrentSpace();
        game.move(game.getDice().getSum());
        assertNotEquals(previousPos, game.getCurrentPlayer().getPosition());
        assertNotEquals(previousSpace, game.getCurrentSpace());
    }*/

    @Test
    void nextPlayerTest() {
        game.getPlayers().add(new Player(2,1500));
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
        assertTrue(game.getCurrentPlayer().getPosition() == 10);
    }

    @Test
    void testJailTurn() {
        gameSettings.addPlayer();
        Player player = gameSettings.getPlayers().get(0);
        player.moveTo(10, false);
        player.setTurnsInJail(1);
        game = new Game(gameSettings);

        game.move(game.getDice().getSum());

        if(game.getDice().isDoubles()) {
            assertEquals(game.getDice().getSum(), player.getPosition()-10);
            assertEquals(0, player.getTurnsInJail());
        }
        else {
            assertEquals(10, player.getPosition());
            assertEquals(2, player.getTurnsInJail());
        }


    }
}
