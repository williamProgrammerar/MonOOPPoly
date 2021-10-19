package Controller;

import Model.Game;
import Model.Locale;
import Model.Space;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class BuyHouseController {
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

    public void buyHouse(){
       Locale locale = (Locale) game.getSelectedSpace();
    }
}
