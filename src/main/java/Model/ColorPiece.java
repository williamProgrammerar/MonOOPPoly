package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ColorPiece {
    private final Image image;
    private final Color color;

    public ColorPiece(Image image, Color color) {
        this.image = image;
        this.color = color;
    }

    public Image getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }
}
