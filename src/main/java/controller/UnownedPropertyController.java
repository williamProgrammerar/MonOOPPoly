package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 *When a player lands on unowned property, this class add a choice for the player to either
 * buy the property or set it up for auction for other players to bid on it.
 *
 * @author williamProgrammerar
 * @author rhedinh
 */
public class UnownedPropertyController {
    private @FXML
    FlowPane propertyFlowPane;

    private final BoardController boardController;

    public UnownedPropertyController(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     * If the player chooses to but the property, the property is assinged to the player
     * and clears the flowpane so that the options is not available any more and
     * also makes it possible for the player to end it's turn if it want to.
     */
    public void buyProperty() {
        boardController.buyProperty();
        boardController.clearBoardFlowPane();
        boardController.makeEndTurnClickable();
    }

    /**
     * changes fxml file shown in boardFlowPane in boardController to show the auction fxml file
     */
    public void startAuction() {
        System.out.println("Start auction");
        boardController.showAuctionView();
    }

    public void setFlowPane(AnchorPane anchorPane) {
        clearFlowPane();
        addToFlowPane(anchorPane);
    }

    private void clearFlowPane() {
        propertyFlowPane.getChildren().clear();
    }

    private void addToFlowPane(AnchorPane anchorPane) {
        propertyFlowPane.getChildren().add(anchorPane);
    }
}
