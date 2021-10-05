package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlayerSetUpController extends AnchorPane {
    private ObservableList<String> playerTypes = FXCollections.observableArrayList("Human", "Computer", "None");
    @FXML
    private Button nextButton;

    @FXML
    private TextField pNameTextField;

    @FXML
    private Button prevButton;

    @FXML
    private ComboBox<String> playerTypeCBox;

    @FXML
    private ImageView pieceSelImageView;


    public PlayerSetUpController(int playerID) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/playerSetUp.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        pNameTextField.setText("Player " + playerID);
        playerTypeCBox.setItems(playerTypes);

    }
}
