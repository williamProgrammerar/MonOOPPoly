package Controller;

import Model.Locale;
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
import java.util.ResourceBundle;


public class spaceController<T> extends AnchorPane {
    @FXML
    Text localeName;
    @FXML Text localePrice;
    @FXML
    Rectangle localeColor;
    @FXML
    Rectangle spaceStroke;
    @FXML
    SplitPane splitSpace;
    @FXML AnchorPane ap;

    private Space space;

    public spaceController(Space space, int c) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MonopolySpace.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.space = space;
        localeName.setText(space.getSpaceName());
        localeColor.setStroke(Color.BLACK);
        spaceStroke.setStroke(Color.BLACK);
        //System.out.println(space.getClass().equals(((Model.Locale)space)));

        if (c == 0) {
            ap.setRotate(90);
            localeName.setRotate(-90);
            localePrice.setRotate(-90);
        } else if (c == 10) {
            ap.setRotate(-90);
            localeName.setRotate(90);
            localePrice.setRotate(90);
        }

    }

    private void setOwner(Color c) {
        localeColor.setStroke(c);
        //spaceTile.setStroke(c);
    }
}
