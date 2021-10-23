package controller;

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

/**
 * @author JonEmilsson
 */
public class PlayerSetUpController extends AnchorPane {
    private ObservableList<String> playerTypes = FXCollections.observableArrayList("Human");

    @FXML
    private Button nextButton;

    public TextField getpNameTextField() {
        return pNameTextField;
    }

    public void setpNameTextField(TextField pNameTextField) {
        this.pNameTextField = pNameTextField;
    }

    public ComboBox<String> getPlayerTypeCBox() {
        return playerTypeCBox;
    }

    public void setPlayerTypeCBox(ComboBox<String> playerTypeCBox) {
        this.playerTypeCBox = playerTypeCBox;
    }

    @FXML
    private TextField pNameTextField;

    @FXML
    private Button prevButton;

    @FXML
    private ComboBox<String> playerTypeCBox;

    @FXML
    private ImageView pieceSelImageView;

    /**
     * Simply creates a playerSetUpController and applies that to a newly created Fxml file. Sets info in
     * ComboBoxes and the predetermined player name to player ID.
     * @param playerID Player ID is used to set the name.
     */
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
        playerTypeCBox.getSelectionModel().selectFirst();
    }
}
