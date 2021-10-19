package Controller;

import Model.Game;
import Model.Locale;
import Model.Space;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class BuyHouseController {
    Locale locale;
    public BuyHouseController(Locale locale){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buyHouse.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.locale = locale;
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
    public void buyHouse(){
        locale.buildHouse();
    }
}
