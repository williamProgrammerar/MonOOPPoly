package modelTests;

import model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private final RollDice dice = new RollDice(2,6);
    private final Board board = new Board(dice);

    @Test
    void findSpaceTest() {
        List<Space> spaces = board.getSpaceList();
        String string = "HUBBEN 2.1";
        assertEquals(spaces.get(board.findSpace(string)), spaces.get(39));
    }

    @Test
    void testPropertyList() {
        assertEquals("BASEN", board.getPropertyList().get(0).getSpaceName());
    }
}
