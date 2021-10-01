package Controller;

import Model.Locale;
import Model.Space;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class spaceController extends AnchorPane{
    @FXML
    Text localeName;
    @FXML
    Rectangle localeColor;
    @FXML
    Rectangle spaceTile;

    private Space space;

    public spaceController(Space space, boardController boardController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MonopolySpace.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.space = space;
        //this.parentController = recipeSearchController;
        localeName.setText(space.getSpaceName());
    }
/*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localeColor.setStroke(Color.BLACK);
        spaceTile.setStroke(Color.BLACK);
    }*/

    private void setOwner(Color c) {
        localeColor.setStroke(c);
        spaceTile.setStroke(c);
    }
}
