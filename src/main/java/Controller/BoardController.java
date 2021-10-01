package Controller;

import Model.Board;
import Model.Dice;
import Model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class BoardController implements Initializable {
    Game game;
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
    @FXML
    SpaceController space1Controller;
    //@FXML spaceController space2Controller;
    @FXML
    SpaceController space3Controller;
    @FXML
    SpaceController space6Controller;
    @FXML
    SpaceController space8Controller;
    @FXML
    SpaceController space9Controller;
    @FXML
    SpaceController space11Controller;
    @FXML
    SpaceController space13Controller;
    @FXML
    SpaceController space14Controller;
    @FXML
    SpaceController space16Controller;
    @FXML
    SpaceController space18Controller;
    @FXML
    SpaceController space19Controller;
    @FXML
    SpaceController space21Controller;
    @FXML
    SpaceController space23Controller;
    @FXML
    SpaceController space24Controller;
    @FXML
    SpaceController space26Controller;
    @FXML
    SpaceController space28Controller;
    @FXML
    SpaceController space29Controller;
    @FXML
    SpaceController space31Controller;
    @FXML
    SpaceController space33Controller;
    @FXML
    SpaceController space34Controller;
    @FXML
    SpaceController space37Controller;
    @FXML
    SpaceController space39Controller;
    private final List<SpaceController> SpaceControllers = new ArrayList<>() {{
        add(space1Controller);
        add(space3Controller);
        add(space6Controller);
        add(space8Controller);
        add(space9Controller);
        add(space11Controller);
        add(space13Controller);
        add(space14Controller);
        add(space16Controller);
        add(space18Controller);
        add(space19Controller);
        add(space3Controller);
        add(space21Controller);
        add(space23Controller);
        add(space24Controller);
        add(space26Controller);
        add(space28Controller);
        add(space29Controller);
        add(space31Controller);
        add(space33Controller);
        add(space34Controller);
        add(space37Controller);
        add(space39Controller);
    }};


    public BoardController() throws Exception {
    }

    public BoardController(Game game, Board board, Dice dice) {
        this.game = game;
        this.board = board;
        this.dice = dice;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initLocaleNames();
        initLocaleColors();
    }

    public void rollDice() {
        dice.rollDice();
        piece.setX(piece.getX() + dice.getSum() * 10);
        dice1.setText(String.valueOf(dice.getDice1()));
        dice2.setText(String.valueOf(dice.getDice2()));
    }

    private void initLocaleNames() {
        space1Controller.localeName.setText(board.getSpace(1).getSpaceName());
        space3Controller.localeName.setText(board.getSpace(3).getSpaceName());
        space6Controller.localeName.setText(board.getSpace(6).getSpaceName());
        space8Controller.localeName.setText(board.getSpace(8).getSpaceName());
        space9Controller.localeName.setText(board.getSpace(9).getSpaceName());
        space21Controller.localeName.setText(board.getSpace(22).getSpaceName());
        space23Controller.localeName.setText(board.getSpace(24).getSpaceName());
        space24Controller.localeName.setText(board.getSpace(25).getSpaceName());
        space26Controller.localeName.setText(board.getSpace(27).getSpaceName());
        space28Controller.localeName.setText(board.getSpace(29).getSpaceName());
        space29Controller.localeName.setText(board.getSpace(30).getSpaceName());
        if (space11Controller.ap.getRotate() != 0) {
            space11Controller.localeName.setRotate(-90);
        }
    }

    private void initLocaleColors() {
        space1Controller.localeColor.setFill(Color.web("#FA6601"));
        space3Controller.localeColor.setFill(Color.web("#FA6601"));
        space6Controller.localeColor.setFill(Color.BROWN);
        space8Controller.localeColor.setFill(Color.BROWN);
        space9Controller.localeColor.setFill(Color.BROWN);
        space21Controller.localeColor.setFill(Color.GREEN);
        space23Controller.localeColor.setFill(Color.GREEN);
        space24Controller.localeColor.setFill(Color.GREEN);
        space26Controller.localeColor.setFill(Color.DARKGRAY);
        space28Controller.localeColor.setFill(Color.DARKGRAY);
        space29Controller.localeColor.setFill(Color.DARKGRAY);
    }
    public void initGame(Game game){
        this.game = game;
    }
}
