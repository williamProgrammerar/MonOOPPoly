package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class SpaceController {
    @FXML
    Text localeName;
    @FXML
    Rectangle localeColor;
    @FXML
    AnchorPane ap;

    private void setOwner(Color c){
        localeColor.setStroke(c);

    }
}
