package Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PieceController {

    private final List<Image> images = new ArrayList<>();
    private final Random rand = new Random();

    public PieceController() {
        initImages();
    }

    public ImageView createPiece() {
        return new ImageView(selectPiece());
    }

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
}
