import Model.Board;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test {
    List<String> list = new ArrayList<>();
    Board board = new Board();
    Random rand = new Random();
    Player player = new Player(1);

    public test() throws Exception {
    }

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
        String spaceName;
        int position;
        for (int i = 0; i < 100; i++) {
            spaceName = board.getSpace(rand.nextInt(40)).getSpaceName();
            position = findSpace(spaceName);
            System.out.println(spaceName + " is located at position: " + position);
        }
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

    @Test
    void testMove() {
        player.move(20);
        System.out.println(player.getPosition());
        System.out.println(player.HasPassedGo());
        player.move(20);
        System.out.println(player.getPosition());
        System.out.println(player.HasPassedGo());
        player.move(-5);
        System.out.println(player.getPosition());
        System.out.println(player.HasPassedGo());
    }

    @Test
    void testMoveTo() {
        player.moveTo(20,true);
        System.out.println(player.getPosition());
        System.out.println(player.HasPassedGo());
        player.moveTo(15,true);
        System.out.println(player.getPosition());
        System.out.println(player.HasPassedGo());
        player.moveTo(10,false);
        System.out.println(player.getPosition());
        System.out.println(player.HasPassedGo());
    }
}