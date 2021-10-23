package view;

import model.Player;
import javafx.scene.image.ImageView;

public class Piece {

    private final ImageView piece;
    private final Player player;

    public Piece(ImageView piece, Player player) {
        this.piece = piece;
        this.player = player;
    }

    public ImageView getPiece() {
        return piece;
    }

    public Player getPlayer() {
        return player;
    }
}
