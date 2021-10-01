package Controller;

import Model.Board;
import Model.Dice;
import Model.Game;
import Model.Space;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;


public class boardController implements Initializable {
    Game game = new Game();
    Board board = game.getBoard();
    Dice dice = game.getDice();
    // everything involving controlling the dice should be moved here and removed from the Game class.
    @FXML
    Button dice1;
    @FXML
    Button dice2;
    @FXML
    ImageView piece = new ImageView(new Image("pic/Asmurf-icon.png"));
    @FXML
    GridPane boardGrid;

    /*public boardController() throws Exception {
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateRecipeList();
    }

    private void updateRecipeList() {
        List<Space> spaceList = board.getSpaceList();

        boardGrid.getChildren().clear();

        int r = 10;
        int c = 10;
        for (Space space : spaceList) {
            spaceController spaceController = new spaceController(space, c);
            boardGrid.add(spaceController, c, r);

            if (r == 10 && c != 0) {
                c--;
            } else if (c == 0 && r != 0) {
                r--;
            } else if (r == 0 && c != 10) {
                c++;
            } else if (c == 10 && r != 10) {
                r++;
            }
        }
    }

    public void rollDice() {
        dice.rollDice();
        piece.setX(piece.getX() + dice.getSum() * 10);
        dice1.setText(String.valueOf(dice.getDice1()));
        dice2.setText(String.valueOf(dice.getDice2()));
    }
}
