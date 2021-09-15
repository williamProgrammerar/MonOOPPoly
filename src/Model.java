import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Model {
    Dice dice = new Dice();

    @FXML
    private Text diceText;

    @FXML
    private Circle playerPiece;

    @FXML
    Button dice1;

    @FXML
    Button dice2;

    public void rollDice() {
        dice.rollDice();
        diceText.setText("The sum of the two dices rolled is: " + dice.getSum());
        playerPiece.setCenterX(playerPiece.getCenterX() - (dice.getSum() * 10));
        dice1.setText(String.valueOf(dice.getDice1()));
        dice2.setText(String.valueOf(dice.getDice2()));
    }
}
