package View;

import Model.ColorPiece;
import Model.Player;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Piece {
    private final ImageView piece;
    private final Player player;

    public Piece(ColorPiece colorPiece, Player player) {
        this.piece = colorPiece.getImage();
        this.player = player;
        assignColor(colorPiece.getColor());
    }

    public void assignColor(Color c){
        player.setColor(c);
    }

    public ImageView getPiece() {
        return piece;
    }

    public Player getPlayer() {
        return player;
    }
}
