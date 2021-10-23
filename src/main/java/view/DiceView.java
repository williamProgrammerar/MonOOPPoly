package view;

import model.RollDice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DiceView extends AnchorPane {

    private @FXML
    ImageView dice1;

    private @FXML
    ImageView dice2;

    private final RollDice dice;

    private final Map<Integer,Image> diceImages = new HashMap<>();

    public DiceView(RollDice dice) {
        setUpFxml();
        this.dice = dice;
        initDiceImages();
        initDice();
    }

    private void setUpFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Dice.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void initDiceImages() {
        diceImages.put(1, new Image("pic/dice1.png"));
        diceImages.put(2, new Image("pic/dice2.png"));
        diceImages.put(3, new Image("pic/dice3.png"));
        diceImages.put(4, new Image("pic/dice4.png"));
        diceImages.put(5, new Image("pic/dice5.png"));
        diceImages.put(6, new Image("pic/dice6.png"));
    }

    private void initDice() {
        dice1.setImage(diceImages.get(1));
        dice2.setImage(diceImages.get(1));
    }

    public void updateDice() {
        updateDice1();
        updateDice2();
    }

    private void updateDice1() {
        dice1.setImage(diceImages.get(dice.getSpecificDieValue(0)));
    }

    private void updateDice2() {
        dice2.setImage(diceImages.get(dice.getSpecificDieValue(1)));
    }
}
