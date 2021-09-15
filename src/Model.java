import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.List;

public class Model {
    Dice dice = new Dice();
    List<Player> players;

    @FXML
    ImageView piece = new ImageView(new Image("Images/Asmurf-icon.png"));

    @FXML
    private Text diceText;

    private Player player = new Player(0,choosePiece(),0);

    public Model() throws Exception {
    }

    public void choosePlayers(int amount) throws Exception {
        for(int i = 0; i - 1 < amount; i++){
            players.add(new Player(0,choosePiece(),0));
        }
    }
    public int choosePiece(){
        return 1;
    }

    public void rollDice() {
        dice.rollDice();
        diceText.setText("The sum of the two dices rolled is: " + dice.getSum());
        piece.setX(piece.getX() + dice.getSum()*10);
        //player.move(dice.getSum()*10); //.image.setCenterX(playerPiece.getCenterX() + (dice.getSum() * 10));
    }
}
