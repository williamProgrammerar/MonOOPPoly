package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * @author williamProgrammerar
 */
public class UnownedPropertyController {
    private @FXML
    FlowPane propertyFlowPane;

    private final BoardController boardController;

    public UnownedPropertyController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void buyProperty() {
        boardController.buyProperty();
        boardController.clearBoardFlowPane();
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
