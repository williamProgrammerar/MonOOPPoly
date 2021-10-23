package View;

import Controller.TradeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author williamProgrammerar
 */
public class TradeView extends AnchorPane {
    public TradeView(TradeController tradeController) {
        setUpFxml(tradeController);
    }

    private void setUpFxml(TradeController tradeController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Trade.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(tradeController);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
