import Model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    List<String> list = new ArrayList<>();
    Board board = new Board();
    @BeforeEach
    void setUp() {
        list.add("William");
        list.add("Hampus");
        list.add("Jon");
        list.add("Vilhelm");
    }

    @Test
    void testChangePlayer() {
        for (int r = 0; r < 10; r++) {
            for (String s : list) {
                System.out.println(s);
            }
            String tmp = list.get(0);
            list.remove(0);
            list.add(tmp);
            System.out.println();
        }
    }

    @Test
    void testGetPositionUsingString() {
        System.out.println(findSpace("BASEN"));
        System.out.println(findSpace("HUBBEN 2.1"));
        System.out.println(findSpace("GO"));
        System.out.println(findSpace("KEMIGÅRDEN"));
    }

    int findSpace(String string) {
        int position = 0;
        for (int i = 0; i < board.getSpaceList().size(); i++) {
            if (board.getSpace(i).getSpaceName().equals(string)) {
                position = i;
                break;
            }
        }
        return position;
    }
}