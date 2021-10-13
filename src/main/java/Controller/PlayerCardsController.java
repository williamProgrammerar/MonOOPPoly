package Controller;

import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controlls the playercards
 *
 * @author rhedinh
 */

public class PlayerCardsController extends AnchorPane {
    @FXML
    Text capitalLabel;
    @FXML Text nameLabel;

    public PlayerCardsController(Player player) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PlayerCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        capitalLabel.setText(player.getCapital() + " kr");
        nameLabel.setText(player.getName());
    }

    public void updateCapital(Player player){
        capitalLabel.setText(player.getCapital() + "kr");
    }
}
