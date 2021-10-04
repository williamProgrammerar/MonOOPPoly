package Controller;

import Model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SetUpPlayerController {
    @FXML
    FlowPane flowPane;

    @FXML
    public void startGame(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ChalmersMonopoly.fxml"));
        Parent monopolyParent = loader.load();
        Scene monopolyScene = new Scene(monopolyParent);
        BoardController controller = loader.getController();
        controller.initGame(new Game(2));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(monopolyScene);
        window.show();
    }

    private int checkNumberOfplayers() {
        return 1;
    }

    @FXML public void goBack(ActionEvent event) throws IOException {
        Parent monopolyParent = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        Scene monopolyScene = new Scene(monopolyParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(monopolyScene);
        window.show();
    }

    @FXML public void addPlayer(ActionEvent event){
        flowPane.getChildren().add(new PlayerSetUpController(2));


    }

    @FXML
    private ComboBox<String> playerType1CBox;
    @FXML
    private ComboBox<String> playerType2CBox;
    @FXML
    private ComboBox<String> playerType3CBox;
    @FXML
    private ComboBox<String> playerType4CBox;
    @FXML
    private CheckBox p1Checkbox;
    @FXML
    private CheckBox p2Checkbox;
    @FXML
    private CheckBox p3Checkbox;
    @FXML
    private CheckBox p4Checkbox;

    private ObservableList<String> playerTypes = FXCollections.observableArrayList("Human", "Computer", "None");
    public void initialize() {
        flowPane.getChildren().addAll(new PlayerSetUpController(1));

    }


}
