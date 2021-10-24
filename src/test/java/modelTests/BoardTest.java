package modelTests;

import model.Board;
import model.Space;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private final Board board = new Board();

    @Test
    void findSpaceTest() {
        List<Space> spaces = board.getSpaceList();
        String string = "HUBBEN 2.1";
        assertEquals(spaces.get(board.findSpace(string)), spaces.get(39));
    }
}
