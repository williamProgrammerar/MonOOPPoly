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

    // temporary location, will move to View-class later
    @FXML
    private Text diceText;

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
        diceText.setText("The sum of the two dices rolled is: " + dice.getSum());
        piece.setX(piece.getX() + dice.getSum()*10);
        dice1.setText(String.valueOf(dice.getDice1()));
        dice2.setText(String.valueOf(dice.getDice2()));
    }

    public void move() {

    }

    public void next() {
        //currentPlayer = players.get(0);
        //players.indexOf(currentPlayer);
    }
}
