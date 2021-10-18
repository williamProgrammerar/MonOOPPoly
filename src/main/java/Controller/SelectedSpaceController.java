package Controller;

import Model.Game;
import Model.Locale;
import Model.Space;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class SelectedSpaceController {
    public SelectedSpaceController(Space space){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buyHouse.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
    public void buyHouse(Locale locale){

    }
}
