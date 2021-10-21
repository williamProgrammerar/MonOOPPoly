package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * View for the spaces
 *
 * @author rhedinh
 */


public class SpaceView extends AnchorPane {
    @FXML
    Text localeName;
    @FXML
    Text localePrice;

    Game game;
    Space space;

    SelectedLocaleController buyHouseController;
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

    public SpaceView(Space space, Game game) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MonopolySpace.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.space = space;
        this.game = game;
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

    }

    @FXML
    public void spaceSelected(){
        game.setSelectedSpace((Property) space);
        System.out.println("I was Clicked");
    }

    public void setOwner(Player player) {
        Color color = player.getColor();
        localeColor.setStroke(color);
        spaceStroke.setStroke(color);
    }

    public AnchorPane getSpaceText() {
        return spaceText;
    }
}
