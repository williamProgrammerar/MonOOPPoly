package controller;

import model.Game;
import model.Locale;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author JonEmilsson
 * @author williamProgrammerar
 */
public class BuyHouseController extends AnchorPane {
    private final Game game;
    private final BoardController boardController;

    public BuyHouseController(Game game, BoardController boardController) {
        this.boardController = boardController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buyHouse.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.game = game;
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * This method simply calls for game to buy house, when buy house is clicked.
     */
    public void buyHouse() {
        Locale locale = (Locale) game.getSelectedSpace(); // should probably not cast and this should be changed
        game.buyHouse(locale);
        buildHouse(locale);
    }

    private void buildHouse(Locale locale) {
        if (game.getCurrentPlayer().hasMonopoly(locale) && game.getCurrentPlayer().getCapital() >= locale.getHouseCost()) {
            boardController.buildBuilding(locale);
        }
    }
}