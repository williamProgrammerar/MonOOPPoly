package View;

import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece {

    private final Player player;

    @FXML
    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Piece(int pieceID, Player player) throws Exception {
        //this.img = new ImageView(gatherImage(pieceID));
        this.player = player;
    }

    public Image gatherImage(int pieceID)  throws Exception  {
        switch(pieceID) {
            case 1: return new Image("pic/Asmurf-icon.png");
        }
        throw new Exception("Invalid Piece ID");
    }

    public void updatePosition() {
        System.out.println("Piece for player " + player.getPlayerId() + " has been updated.");
    }
}
