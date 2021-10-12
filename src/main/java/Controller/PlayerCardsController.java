package Controller;

import Model.Player;
import View.Piece;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class PlayerCardsController extends AnchorPane {
    @FXML
    Text capitalLabel;
    @FXML Text nameLabel;
    @FXML
    ImageView playerIcon;

    public PlayerCardsController(Piece piece) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PlayerCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        capitalLabel.setText(piece.getPlayer().getCapital() + " kr");
        nameLabel.setText(piece.getPlayer().getName());
        playerIcon.setImage(piece.getPiece().getImage());
    }

    public void updateCapital(Player player){
        capitalLabel.setText(player.getCapital() + "kr");
    }
}
