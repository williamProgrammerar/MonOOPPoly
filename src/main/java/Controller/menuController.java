package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class menuController {
    @FXML private Button startGameButton;

    @FXML
    public void startGame(ActionEvent event) throws IOException {
        Parent monopolyParent = FXMLLoader.load(getClass().getResource("/fxml/ChalmersMonopoly.fxml"));
        Scene monopolyScene = new Scene(monopolyParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(monopolyScene);
        window.show();
    }
    @FXML public void exitGame(ActionEvent event){
        System.exit(0);
    }
}
