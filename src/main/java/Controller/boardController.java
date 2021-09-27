package Controller;

import Model.Board;
import Model.Dice;
import Model.Game;
import Model.Space;
import Model.Dice;
import Model.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;


public class boardController implements Initializable {
    Game game = new Game();
    Board board = new Board();
    Dice dice;
    // everything involving controlling the dice should be moved here and removed from the Game class.
    @FXML
    Button dice1;
    @FXML
    Button dice2;
    @FXML
    ImageView piece = new ImageView(new Image("pic/Asmurf-icon.png"));
    @FXML
    GridPane boardGrid;
    @FXML
    spaceController space1Controller;
    @FXML spaceController space2Controller;

    public boardController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        space1Controller.hejText.setText(board.getSpace(0).getSpaceName());
    }

    public void rollDice() {
            dice.rollDice();
            piece.setX(piece.getX() + dice.getSum()*10);
            dice1.setText(String.valueOf(dice.getDice1()));
            dice2.setText(String.valueOf(dice.getDice2()));
        }
}
