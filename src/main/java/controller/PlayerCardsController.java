package controller;

import model.Player;
import view.PieceView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controls the playercards
 *
 * @author rhedinh
 */

public class PlayerCardsController extends AnchorPane {
    @FXML
    Text capitalLabel;

    @FXML
    Text nameLabel;

    @FXML
    ImageView playerIcon;

    @FXML
    Rectangle playersTurn;

    @FXML
    Rectangle playerColor;

    public PlayerCardsController(PieceView pieceView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PlayerCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        capitalLabel.setText(pieceView.getPlayer().getCapital() + " kr");
        nameLabel.setText(pieceView.getPlayer().getName());
        playerIcon.setImage(pieceView.getPiece().getImage());
        playerColor.setFill(pieceView.getPlayer().getColor());
    }

    public void updateCapital(Player player) {
        capitalLabel.setText(player.getCapital() + "kr");
    }

    public void updateCurrentPlayer(boolean b){
        playersTurn.setVisible(b);
    }
}
