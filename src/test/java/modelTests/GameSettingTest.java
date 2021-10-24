package modelTests;

import model.GameSettings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameSettingTest {
    GameSettings gs = new GameSettings();

    void addPlayers(int k) {
        for (int i = 0; i < k; i++) {
            gs.addPlayer();
        }
    }

    @Test
    void addPlayersTest() throws Exception {
        try {
            addPlayers(4);
        } catch (Exception e) {
            assertEquals(gs.getPlayers().size(), 4);
        }

        try {
            addPlayers(4);
        } catch (IllegalArgumentException e) {
            assertEquals(gs.getPlayers().size(), 4);
        }
    }

    @Test
    void playerInfoTest() throws Exception {
        List<String> names = new ArrayList<>();
        List<String> stateList = new ArrayList<>();
        names.add("Bert");
        names.add("Mark");
        names.add("Lisa");
        names.add("Adam");
        stateList.add("Human");
        stateList.add("Human");
        stateList.add("Human");
        stateList.add("Human");
        addPlayers(3);
        gs.setPlayerInfo(names,stateList);
        assertTrue(gs.checkPlayers());
    }

    @Test
    void checkPlayersTest() {
        addPlayers(1);
        gs.getPlayers().get(0).setName("");
        assertFalse(gs.checkPlayers());
    }

    @Test
    void testRemovePlayer() {
        addPlayers(3);

        gs.removePlayer();
        assertEquals(2, gs.getPlayers().size());

        try {
            gs.removePlayer();
        } catch (IllegalArgumentException e) {
            assertEquals(2, gs.getPlayers().size());
        }
    }

    @Test
    void testStartCapitalSetAndGet() {
        int temp = 2000;
        gs.setStartCapital(temp);
        assertEquals(temp, gs.getStartCapital());
    }
}
