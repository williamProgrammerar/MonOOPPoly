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
 * Every player has a assigned playercard which shows its name, capital, piece and wheater
 * it's its turn or not. This is the controller for the .fxml-file of the cards.
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
        playerColor.setFill(pieceView.getColor());
    }

    /**
     * Updates the capital of a player graphically in the playercards.
     *
     * @param player the player which should have an updated visual of its capital
     */
    public void updateCapital(Player player) {
        capitalLabel.setText(player.getCapital() + "kr");
    }


    /**
     * Adds or removes the stroke around the player card which
     * is used to inform the player of wheter it's its turn or not
     *
     * @param b if true, the stroke is visable and it's the players turn
     *          if false, the stroke is not visable and it's not the players turn.
     */
    public void updateCurrentPlayer(boolean b){
        playersTurn.setVisible(b);
    }
}
