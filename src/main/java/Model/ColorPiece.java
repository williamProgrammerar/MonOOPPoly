package Model;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ColorPiece {
    private final ImageView image;
    private final Color color;

    public ColorPiece(ImageView image, Color color) {
        this.image = image;
        this.color = color;
    }

    public ImageView getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }
}
