package Controller;

import Model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class boardController {
        Game game = new Game();
        // everything involving controlling the dice should be moved here and removed from the Game class.
        @FXML
        Button dice1;
        @FXML
        Button dice2;
        @FXML
        ImageView piece = new ImageView(new Image("pic/Asmurf-icon.png"));

    public boardController() throws Exception {
    }

    public void rollDice() {
            game.dice.rollDice();
            piece.setX(piece.getX() + game.dice.getSum()*10);
            dice1.setText(String.valueOf(game.dice.getDice1()));
            dice2.setText(String.valueOf(game.dice.getDice2()));
        }
}
