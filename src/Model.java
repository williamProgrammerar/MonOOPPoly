import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Model {
    Dice dice = new Dice();

    @FXML
    private Text diceText;

    @FXML
    private Circle playerPiece;

    public void rollDice() {
        dice.rollDice();
        diceText.setText("The sum of the two dices rolled is: " + dice.getSum());
        playerPiece.setCenterX(playerPiece.getCenterX() + (dice.getSum() * 10));
    }
}
