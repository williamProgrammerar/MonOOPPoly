import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class test {
    private List<String> list = new ArrayList<>();
    private Random rand = new Random();
    private Player player = new Player(1,1500);
    private GameSettings gameSettings = new GameSettings();
    private Game game;
    private Board board = game.getBoard();
    private Dice dice = game.getDice();
    private List<Player> players = game.getPlayers();

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

    @Test
    void testPlayerTurn() throws Exception {
        gameSettings.addPlayer();
        gameSettings.addPlayer();
        game = new Game(gameSettings);
        System.out.println("Current amount of players: " + players.size());

        for (int i = 0; i < 20; i++) {
            System.out.println();
            dice.rollDice();
            System.out.println("Dice rolled: " + dice.getSum());
            game.move(dice.getSum());
            game.next();
        }
    }

    @Test
    void testU() {
        gameSettings.addPlayer();
        Player player = gameSettings.getPlayers().get(0);
        player.moveTo(23, true);
        game.move(7);
        
    }

    @Test
    void testJailTurn() {
        gameSettings.addPlayer();
        player.moveTo(10, false);
        player.setTurnsInJail(1);
        game = new Game(gameSettings);

        game.move(game.getDice().getSum());

        //if(player.)

    }
}