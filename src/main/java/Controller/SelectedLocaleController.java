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
public class SelectedLocaleController extends AnchorPane {
    Game game;
    Locale locale;
    public SelectedLocaleController(Game game){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buyHouse.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.game = game;
        locale = (Locale) game.getSelectedSpace();
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
        game.buyHouse(locale);
    }
    public void mortgageProperty(){
        game.mortgageLocale();
    }
}
