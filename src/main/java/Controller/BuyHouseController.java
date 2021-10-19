package Controller;

import Model.Game;
import Model.Locale;
import Model.Space;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author JonEmilsson
 */
public class BuyHouseController extends AnchorPane {
    Game game;
    public BuyHouseController(Game game){
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
    public void buyHouse(){
        Locale locale = (Locale) game.getSelectedSpace(); // should probably not cast and this should be changed
        game.buyHouse(locale);
    }
}
