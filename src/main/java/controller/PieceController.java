package controller;

import view.ColorPiece;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * Controls the pieces and colors for the players.
 * When a game starts, every player is rabdomly assigned
 * a piece and a matching color of the piece to visually
 * seperate the different players.
 *
 * @author rhedinh
 */

public class PieceController {

    private final List<ColorPiece> colorPieceList = new ArrayList<>();
    private final Random rand = new Random();

    /**
     * Constructor calls initImages to initiate the list of pieces.
     */
    public PieceController() {
        initImagesAndColors();
    }

    public ColorPiece createPiece() {
        return selectPiece();
    }

    /**
     * Randomly selects a piece from the images list and then removes it so the same piece doesn't get selected again.
     * @return returns a random image from images list
     */
    private ColorPiece selectPiece() {
        int i = rand.nextInt(colorPieceList.size());
        ColorPiece colorPiece = colorPieceList.get(i);
        colorPieceList.remove(i);
        return colorPiece;
    }

    /**
     * A list of every possible piece and matching color a player possibly can have.
     */
    private void initImagesAndColors() {
        colorPieceList.add(new ColorPiece(new ImageView("pic/Asterix.png"), Color.SADDLEBROWN));
        colorPieceList.add(new ColorPiece(new ImageView("pic/HackeHackspett.png"), Color.ORANGE));
        colorPieceList.add(new ColorPiece(new ImageView("pic/KalleAnkaa.png"), Color.ROYALBLUE));
        colorPieceList.add(new ColorPiece(new ImageView("pic/LuckyLuke.png"), Color.DARKGRAY));
        colorPieceList.add(new ColorPiece(new ImageView("pic/Mario.png"), Color.RED));
        colorPieceList.add(new ColorPiece(new ImageView("pic/Smurf.png"), Color.TURQUOISE));
    }
}
