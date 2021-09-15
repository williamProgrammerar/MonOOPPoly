package View;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece {
    @FXML
    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Piece(int pieceID) throws Exception {
        this.img = new ImageView(gatherImage(pieceID));

    }
    public Image gatherImage(int pieceID)  throws Exception  {
        switch(pieceID) {
            case 1: return new Image("Images/Asmurf-icon.png");
        }
        throw new Exception("Invalid Piece ID");
    }
}
