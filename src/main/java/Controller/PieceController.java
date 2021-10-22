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
        initImages();
        initImageColorMap();
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
        colorPieceList.add(new ColorPiece(new Image("pic/Asterix.png"), Color.SADDLEBROWN));
        colorPieceList.add(new ColorPiece(new Image("pic/HackeHackspett.png"), Color.ORANGE));
        colorPieceList.add(new ColorPiece(new Image("pic/KalleAnkaa.png"), Color.ROYALBLUE));
        colorPieceList.add(new ColorPiece(new Image("pic/LuckyLuke.png"), Color.DARKGRAY));
        colorPieceList.add(new ColorPiece(new Image("pic/Mario.png"), Color.RED));
        colorPieceList.add(new ColorPiece(new Image("pic/Smurf.png"), Color.TURQUOISE));
    }

    private void initImages() {
        /*images.add(new Image("pic/Asterix.png"));
        images.add(new Image("pic/HackeHackspett.png"));
        images.add(new Image("pic/KalleAnkaa.png"));
        images.add(new Image("pic/LuckyLuke.png"));
        images.add(new Image("pic/Mario.png"));
        images.add(new Image("pic/Smurf.png"));*/
    }

    private void initImageColorMap(){
        /*imageColorMap.put(new Image("pic/Asterix.png"), Color.SADDLEBROWN);
        imageColorMap.put(new Image("pic/HackeHackspett.png"), Color.ORANGE);
        imageColorMap.put(new Image("pic/KalleAnkaa.png"), Color.ROYALBLUE);
        imageColorMap.put(new Image("pic/LuckyLuke.png"), Color.DARKGRAY);
        imageColorMap.put(new Image("pic/Mario.png"), Color.RED);
        imageColorMap.put(new Image("pic/Smurf.png"), Color.TURQUOISE);*/
    }



    public void assignColor(){

    }
}
