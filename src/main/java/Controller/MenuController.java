package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author JonEmilsson
 */
public class MenuController {
    @FXML private Button startGameButton;

    @FXML
    public void startGame(ActionEvent event) throws IOException {
        Parent monopolyParent = FXMLLoader.load(getClass().getResource("/fxml/setUpPlayer.fxml"));
        Scene monopolyScene = new Scene(monopolyParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(monopolyScene);
        window.show();
    }

    @FXML
    public void exitGame(ActionEvent event){
        System.exit(0);
    }

    @FXML
    private final ComboBox<String> playerType1CBox = new ComboBox<>();

    @FXML
    private ComboBox<String> playerType2CBox;

    @FXML
    private ComboBox<String> playerType3CBox;

    @FXML
    private ComboBox<String> playerType4CBox;

    private final ObservableList<String> playerTypes = FXCollections.observableArrayList("Human", "Computer", "None");

    @FXML
    public void initialize() {
        playerType1CBox.getItems().removeAll(playerType1CBox.getItems());
        playerType1CBox.getItems().addAll("Option A", "Option B", "Option C");
        playerType1CBox.getSelectionModel().select("Option B");
        //playerType2CBox.setItems(playerTypes);
        //playerType3CBox.setItems(playerTypes);
        //playerType4CBox.setItems(playerTypes);
    }
}
