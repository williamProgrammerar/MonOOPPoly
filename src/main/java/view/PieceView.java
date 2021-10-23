package view;

import model.Player;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class PieceView {
    private final ImageView piece;
    private final Player player;
    private final Color color;

    public PieceView(ColorPiece colorPiece, Player player) {
        this.piece = colorPiece.getImage();
        this.player = player;
        this.color = colorPiece.getColor();
    }

    public ImageView getPiece() {
        return piece;
    }

    public Player getPlayer() {
        return player;
    }

    public Color getColor() { return color; }
}
