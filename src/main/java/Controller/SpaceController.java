package Controller;

import Model.Locale;
import Model.Property;
import Model.Space;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;



public class SpaceController extends AnchorPane {

    @FXML
    Text localeName;
    @FXML
    Text localePrice;
    @FXML
    Rectangle localeColor;
    @FXML
    Rectangle spaceStroke;
    @FXML
    SplitPane splitSpace;
    @FXML
    AnchorPane ap;
    @FXML
    AnchorPane spaceText;

    public SpaceController(Space space, int c) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MonopolySpace.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        localeName.setText(space.getSpaceName());
        localeColor.setStroke(Color.BLACK);
        spaceStroke.setStroke(Color.BLACK);

        if (space instanceof Locale) {
            localeColor.setFill(Paint.valueOf(((Locale) space).getSectionColour()));
        } else {
            localeColor.setVisible(false);
        }

        if (space instanceof Property) {
            localePrice.setText(((Property) space).getPrice() + " kr");
        } else {
            localePrice.setVisible(false);
        }

        if (c == 0) {
            ap.setRotate(90);
            spaceText.setRotate(-90);
        } else if (c == 10) {
            ap.setRotate(-90);
            spaceText.setRotate(90);
        }

    }

    private void setOwner(Color c) {
        localeColor.setStroke(c);
        //spaceTile.setStroke(c);
    }
}
