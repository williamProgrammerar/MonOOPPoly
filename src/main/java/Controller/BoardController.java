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

import java.net.URL;
import java.util.*;


public class BoardController  {
    private Game game;
   // Board board = game.getBoard();
  //  Dice dice = game.getDice();
    // everything involving controlling the dice should be moved here and removed from the Game class.
    @FXML
    Button dice1;
    @FXML
    Button dice2;
    @FXML
    ImageView piece = new ImageView(new Image("pic/Asmurf-icon.png"));
    @FXML
    GridPane boardGrid;

    private void initSpaces() {
        List<Space> spaceList = game.getBoard().getSpaceList();

        boardGrid.getChildren().clear();


        int r = 10;
        int c = 10;
        for (Space space : spaceList) {
            SpaceController spaceController = new SpaceController(space, c);
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

    private void initPlayers(){
        boardGrid.add(piece,10,10);
    }

    public void rollDice() {
        game.getDice().rollDice();
        piece.setX(piece.getX() + game.getDice().getSum() * 10);
        dice1.setText(String.valueOf(game.getDice().getDice1()));
        dice2.setText(String.valueOf(game.getDice().getDice2()));
    }

    public void initGame(Game game) {
      this.game = game;
      initSpaces();
      initPlayers();
    }
}