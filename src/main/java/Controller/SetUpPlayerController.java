package Controller;

import Model.Game;
import Model.GameSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetUpPlayerController {
    GameSettings gameSettings = new GameSettings();
    private List<String> nameList = new ArrayList<>();
    private List<String> stateList = new ArrayList<>();
    @FXML
    FlowPane flowPane;

    @FXML
    public void startGame(ActionEvent event) throws Exception {
        updatePlayerInfo();
        gameSettings.setPlayerInfo(nameList,stateList);
       if(gameSettings.checkPlayers()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/ChalmersMonopoly.fxml"));
            Parent monopolyParent = loader.load();
            Scene monopolyScene = new Scene(monopolyParent);
            BoardController controller = loader.getController();
            controller.initGame(new Game(gameSettings));
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(monopolyScene);
            window.show();
      }
       else {
            System.out.println("player info not complete, or valid");
      }

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
    private void updatePlayerInfo(){
        nameList.clear();
        stateList.clear();
        for(Node node : flowPane.getChildren()){
            nameList.add(((PlayerSetUpController)node).getpNameTextField().getText());
            stateList.add(((PlayerSetUpController)node).getPlayerTypeCBox().getValue());
        }
    }
    @FXML public void addPlayer(){
           try{
               gameSettings.addPlayer();
           } catch (IllegalArgumentException e){
               return;
           }
        flowPane.getChildren().add(new PlayerSetUpController(gameSettings.getPlayers().size()));
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
        addPlayer();

    }


}