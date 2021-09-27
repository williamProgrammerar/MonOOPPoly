package Model;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.Queue;

public class Game {
    private Dice dice = new Dice();
    private final Board board = new Board();
    private Queue<Player> players;

    private Player player = new Player(0,choosePiece(),0);

    public Game() throws Exception {
    }

    public void choosePlayers(int amount) throws Exception {
        for(int i = 0; i - 1 < amount; i++){
            players.add(new Player(0,choosePiece(),0));
        }
    }
    public int choosePiece(){
        return 1;
    }

    /**
     * Moves the current player sum spaces.
     */
    public void move() {
        int sum = dice.getSum();
        players.peek().move(sum);
    }

    /**
     * Places the current player (index 0) in a temporary variable.
     * Player index 0 in the player list is then removed, which leads to a new current player.
     * Finally adds the player stored in the temporary variable to the back of the list.
     */
    public void next() {
        Player temporaryPlayer = players.poll();
        players.add(temporaryPlayer);
    }
}
