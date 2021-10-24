package controller;

import model.Game;
import model.GameSettings;
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

/**
 * @author JonEmilsson
 */
public class SetUpPlayerController {
    GameSettings gameSettings = new GameSettings();

    private final List<String> nameList = new ArrayList<>();
    private final List<String> stateList = new ArrayList<>();

    @FXML
    FlowPane flowPane;

    @FXML
    public void startGame(ActionEvent event) throws Exception {
        updatePlayerInfo();
        gameSettings.setPlayerInfo(nameList,stateList);

        if (gameSettings.checkPlayers()) {
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

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent monopolyParent = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        Scene monopolyScene = new Scene(monopolyParent);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(monopolyScene);
        window.show();
    }

    /**
     * This method simply updates the playerinfo that is later sent to game, so that
     * customized names are properly registered.
     */
    private void updatePlayerInfo() {
        nameList.clear();
        stateList.clear();

        for(Node node : flowPane.getChildren()){
            nameList.add(((PlayerSetUpController)node).getpNameTextField().getText());
            stateList.add(((PlayerSetUpController)node).getPlayerTypeCBox().getValue());
        }
    }

    @FXML
    public void addPlayer() {
        try{
            gameSettings.addPlayer();
        } catch (IllegalArgumentException e){
            return;
        }
        flowPane.getChildren().add(new PlayerSetUpController(gameSettings.getPlayers().size()));
    }

    @FXML
    public void removePlayer(){
        try{
            gameSettings.removePlayer();
        } catch (IllegalArgumentException e){
           return;
        }
        flowPane.getChildren().remove(flowPane.getChildren().remove(flowPane.getChildren().size() - 1));
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

    public void initialize() {
        addPlayer();
        addPlayer();
    }
}
