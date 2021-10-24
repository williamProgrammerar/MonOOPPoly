package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class TradeController {

    private final BoardController boardController;

    private @FXML
    AnchorPane tradePane;

    private @FXML
    AnchorPane menuPane;

    private @FXML
    ChoiceBox selectPlayer;

    public TradeController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void switchToTradePane() {
        tradePane.toFront();
    }

    public void switchToMenuPane() {
        menuPane.toFront();
    }
}
