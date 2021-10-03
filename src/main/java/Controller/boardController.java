package Controller;

import Model.Board;
import Model.Dice;
import Model.Game;
import Model.Space;
import View.PieceView;
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

    PieceView pv = new PieceView(game); // This allows PieceView to have access to the same instance of game

    // everything involving controlling the dice should be moved here and removed from the Game class.
    @FXML
    Button dice1;
    @FXML
    Button dice2;
    @FXML
    ImageView piece = pv.createPiece();
    @FXML
    GridPane boardGrid;

    public boardController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSpaces();
        initPlayers();
    }

    private void initSpaces() {
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

    private void initPlayers(){
        boardGrid.add(piece,10,10);
    }

    public void rollDice() {
        dice.rollDice();
        piece.setX(piece.getX() + dice.getSum() * 10);
        dice1.setText(String.valueOf(dice.getDice1()));
        dice2.setText(String.valueOf(dice.getDice2()));
    }

    public void positionToGrid(int position, ImageView piece) {
        int col, row;

        switch (position) {
            case 1 -> {
                col = 9;
                row = 10;
            }
            case 2 -> {
                col = 8;
                row = 10;
            }
            case 3 -> {
                col = 7;
                row = 10;
            }
            case 4 -> {
                col = 6;
                row = 10;
            }
            case 5 -> {
                col = 5;
                row = 10;
            }
            case 6 -> {
                col = 4;
                row = 10;
            }
            case 7 -> {
                col = 3;
                row = 10;
            }
            case 8 -> {
                col = 2;
                row = 10;
            }
            case 9 -> {
                col = 1;
                row = 10;
            }
            case 10 -> {
                col = 0;
                row = 10;
            }
            case 11 -> {
                col = 0;
                row = 9;
            }
            case 12 -> {
                col = 0;
                row = 8;
            }
            case 13 -> {
                col = 0;
                row = 7;
            }
            case 14 -> {
                col = 0;
                row = 6;
            }
            case 15 -> {
                col = 0;
                row = 5;
            }
            case 16 -> {
                col = 0;
                row = 4;
            }
            case 17 -> {
                col = 0;
                row = 3;
            }
            case 18 -> {
                col = 0;
                row = 2;
            }
            case 19 -> {
                col = 0;
                row = 1;
            }
            case 20 -> {
                col = 0;
                row = 0;
            }
            case 21 -> {
                col = 1;
                row = 0;
            }
            case 22 -> {
                col = 2;
                row = 0;
            }
            case 23 -> {
                col = 3;
                row = 0;
            }
            case 24 -> {
                col = 4;
                row = 0;
            }
            case 25 -> {
                col = 5;
                row = 0;
            }
            case 26 -> {
                col = 6;
                row = 0;
            }
            case 27 -> {
                col = 7;
                row = 0;
            }
            case 28 -> {
                col = 8;
                row = 0;
            }
            case 29 -> {
                col = 9;
                row = 0;
            }
            case 30 -> {
                col = 10;
                row = 0;
            }
            case 31 -> {
                col = 10;
                row = 1;
            }
            case 32 -> {
                col = 10;
                row = 2;
            }
            case 33 -> {
                col = 10;
                row = 3;
            }
            case 34 -> {
                col = 10;
                row = 4;
            }
            case 35 -> {
                col = 10;
                row = 5;
            }
            case 36 -> {
                col = 10;
                row = 6;
            }
            case 37 -> {
                col = 10;
                row = 7;
            }
            case 38 -> {
                col = 10;
                row = 8;
            }
            case 39 -> {
                col = 10;
                row = 9;
            }
            default -> {
                col = 10;
                row = 10;
            }
        }
        boardGrid.getChildren().remove(piece);
        boardGrid.add(piece,col,row);
    }
}