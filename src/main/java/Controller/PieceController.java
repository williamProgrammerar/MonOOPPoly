package Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

public class PieceController {

    private final List<Image> images = new ArrayList<>();
    private final List<Image> colors = new ArrayList<>();
    private final Random rand = new Random();
    private final Map<Image, Color> imageColorMap = new HashMap<>();

    /**
     * Constructor calls initImages to initiate the list of pieces.
     */
    public PieceController() {
        initImages();
        initImageColorMap();
    }

    public ImageView createPiece() {
        return new ImageView(selectPiece());
    }

    /**
     * Randomly selects a piece from the images list and then removes it so the same piece doesn't get selected again.
     * @return returns a random image from images list
     */
    private Image selectPiece() {
        int i = rand.nextInt(images.size());
        Image img =  images.get(i);
        images.remove(i);
        return img;
    }

    private void initImages() {
        images.add(new Image("pic/Asterix.png"));
        images.add(new Image("pic/HackeHackspett.png"));
        images.add(new Image("pic/KalleAnkaa.png"));
        images.add(new Image("pic/LuckyLuke.png"));
        images.add(new Image("pic/Mario.png"));
        images.add(new Image("pic/Smurf.png"));
    }

    public void assignColor(){

    }
}
