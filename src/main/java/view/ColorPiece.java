package view;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * A class which matches a color to a piece.
 *
 * @author rhedinh
 */
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
