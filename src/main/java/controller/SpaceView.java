package controller;

import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

import static javafx.scene.paint.Color.*;

/**
 * View for the spaces
 *
 * @author rhedinh
 * @author williamProgrammerar
 */
public class SpaceView extends AnchorPane {
    @FXML
    Text localeName;

    @FXML
    Text localePrice;

    private final Game game;
    private final Space space;

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

    @FXML
    HBox houseHBox;

    private boolean hasHotel;

    public SpaceView(Space space, Game game) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MonopolySpace.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.space = space;
        this.game = game;
        this.hasHotel = false;

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
    public void spaceSelected() {
        game.setSelectedSpace(space);
        System.out.println("I was Clicked");
    }

    public void setOwner(Color c) {
        localeColor.setStroke(c);
        spaceStroke.setStroke(c);
    }

    public void addHouse() {
        if(houseHBox.getChildren().size() < 4 && !hasHotel) {
            Circle circle = new Circle(5, GREEN);
            circle.setStroke(BLACK);
            houseHBox.getChildren().add(circle);
        }
        else {
            addHotel();
        }
    }

    private void addHotel() {
        houseHBox.getChildren().clear();

        Circle circle = new Circle(5, RED);
        circle.setStroke(BLACK);

        houseHBox.getChildren().add(circle);
        hasHotel = true;
    }

    public void clearHouses() {
        houseHBox.getChildren().clear();
        hasHotel = false;
    }

    public AnchorPane getSpaceText() {
        return spaceText;
    }
}
