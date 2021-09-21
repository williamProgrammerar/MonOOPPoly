package Model;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.List;

public class Game {
    private final Dice dice = new Dice();
    private final Board board = new Board();
    private List<Player> players;

    // temporary location, will move to View-class later
    @FXML
    ImageView piece = new ImageView(new Image("pic/Asmurf-icon.png"));

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

    // temporary location, will move to View-class later
    @FXML
    Button dice1;

    // temporary location, will move to View-class later
    @FXML
    Button dice2;

    public void rollDice() {
        dice.rollDice();
        piece.setX(piece.getX() + dice.getSum()*10);
        dice1.setText(String.valueOf(dice.getDice1()));
        dice2.setText(String.valueOf(dice.getDice2()));
    }

    /**
     * Moves the current player sum spaces.
     */
    public void move() {
        int sum = dice.getSum();
        players.get(0).move(sum);
    }

    /**
     * Places the current player (index 0) in a temporary variable.
     * Player index 0 in the player list is then removed, which leads to a new current player.
     * Finally adds the player stored in the temporary variable to the back of the list.
     */
    public void next() {
        Player temporaryPlayer = players.get(0);
        players.remove(0);
        players.add(temporaryPlayer);
    }
}
