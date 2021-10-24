package modelTests;

import model.Jail;
import model.Player;
import model.RollDice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;



public class JailTest {
    private Jail jail;
    private RollDice dice;
    private List<Player> playerList = new ArrayList<>();

    @BeforeEach
    void initJail() {
        dice = new RollDice(2, 6);
        jail = new Jail(50, dice);
        Player player1 = new Player(1, 1500);
        Player player2 = new Player(2, 1500);
        playerList.add(player1);
        playerList.add(player2);
    }

    @Test
    void testAddAndBail() {
        jail.addToJail(playerList.get(0));
        assertTrue(jail.isInJail(playerList.get(0)));
        assertFalse(jail.isInJail(playerList.get(1)));

        jail.bail(playerList.get(0));
        assertFalse(jail.isInJail(playerList.get(0)));
    }

    @Test
    void testJailDouble() {
        jail.addToJail(playerList.get(0));
        dice.setSpecificDieValue(0, 1);
        dice.setSpecificDieValue(1, 1);

        assertFalse(jail.jailTurn(playerList.get(0)));
        assertEquals(1500, playerList.get(0).getCapital());
    }

    @Test
    void testJailStuck() {
        jail.addToJail(playerList.get(0));
        dice.setSpecificDieValue(0, 1);
        dice.setSpecificDieValue(1, 2);

        assertTrue(jail.jailTurn(playerList.get(0)));
        assertEquals(1500, playerList.get(0).getCapital());
    }

    @Test
    void testJail3rdTurn() {
        jail.addToJail(playerList.get(0));
        dice.setSpecificDieValue(0, 1);
        dice.setSpecificDieValue(1, 2);

        for (int i = 0; i < 2; i++) {
            jail.jailTurn(playerList.get(0));
        }

        assertFalse(jail.jailTurn(playerList.get(0)));
        assertEquals(1450, playerList.get(0).getCapital());
    }

    @Test
    void testNotInJail() {
        assertFalse(jail.jailTurn(playerList.get(0)));
    }
}