package view;

import controller.UnownedPropertyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author williamProgrammerar
 */

public class UnownedPropertyView extends AnchorPane {

    public UnownedPropertyView(UnownedPropertyController unownedPropertyController) {
        setUpFxml(unownedPropertyController);
    }

    private void setUpFxml(UnownedPropertyController unownedPropertyController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UnownedProperty.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(unownedPropertyController);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
