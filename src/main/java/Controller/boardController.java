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
    @FXML
    spaceController space1Controller;
    //@FXML spaceController space2Controller;
    @FXML
    spaceController space3Controller;
    @FXML
    spaceController space6Controller;
    @FXML
    spaceController space8Controller;
    @FXML
    spaceController space9Controller;
    @FXML
    spaceController space11Controller;
    @FXML
    spaceController space13Controller;
    @FXML
    spaceController space14Controller;
    @FXML
    spaceController space16Controller;
    @FXML
    spaceController space18Controller;
    @FXML
    spaceController space19Controller;
    @FXML
    spaceController space21Controller;
    @FXML
    spaceController space23Controller;
    @FXML
    spaceController space24Controller;
    @FXML
    spaceController space26Controller;
    @FXML
    spaceController space28Controller;
    @FXML
    spaceController space29Controller;
    @FXML spaceController space31Controller;
    @FXML spaceController space33Controller;
    @FXML spaceController space34Controller;
    @FXML spaceController space37Controller;
    @FXML spaceController space39Controller;
    private final List<spaceController> spaceControllers = new ArrayList<>() {{
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


    public boardController() throws Exception {
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
}
