package Controller;

import Model.ColorPiece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.swing.plaf.ColorUIResource;
import java.util.*;

public class PieceController {

    //private final List<Image> images = new ArrayList<>();
    //private final List<Color> colors = new ArrayList<>();
    private final List<ColorPiece> colorPieceList = new ArrayList<>();
    private final Random rand = new Random();
    //private final Map<Image, Color> imageColorMap = new HashMap<>();

    /**
     * Constructor calls initImages to initiate the list of pieces.
     */
    public PieceController() {
        initImagesAndColors();
    }

    public ColorPiece createPiece() {
        ColorPiece selectedColorPiece = selectPiece();
        return selectedColorPiece;
    }

    /**
     * Randomly selects a piece from the images list and then removes it so the same piece doesn't get selected again.
     * @return returns a random image from images list
     */
    private ColorPiece selectPiece() {
        int i = rand.nextInt(colorPieceList.size());
        ColorPiece colorPiece =  colorPieceList.get(i);
        colorPieceList.remove(i);
        return colorPiece;
    }

    private void initImagesAndColors(){
        colorPieceList.add(new ColorPiece(new ImageView("pic/Asterix.png"), Color.SADDLEBROWN));
        colorPieceList.add(new ColorPiece(new ImageView("pic/HackeHackspett.png"), Color.ORANGE));
        colorPieceList.add(new ColorPiece(new ImageView("pic/KalleAnkaa.png"), Color.ROYALBLUE));
        colorPieceList.add(new ColorPiece(new ImageView("pic/LuckyLuke.png"), Color.DARKGRAY));
        colorPieceList.add(new ColorPiece(new ImageView("pic/Mario.png"), Color.RED));
        colorPieceList.add(new ColorPiece(new ImageView("pic/Smurf.png"), Color.TURQUOISE));
    }
}
