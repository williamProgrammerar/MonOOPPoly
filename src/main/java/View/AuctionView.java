package View;

import Controller.AuctionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author williamProgrammerar
 */
public class AuctionView extends AnchorPane {

    public AuctionView(AuctionController auctionController) {
        setUpFxml(auctionController);
    }

    private void setUpFxml(AuctionController auctionController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AuctionProperty.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(auctionController);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
