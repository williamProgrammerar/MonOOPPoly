package controller;

import model.Auction;
import model.Property;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * @author williamProgrammerar
 */
public class AuctionController {

    private @FXML
    FlowPane auctionFlowPane;

    private @FXML
    Text highestOffer;

    private @FXML
    Text currentBidder;

    private final BoardController boardController;

    private final Auction auction;

    public AuctionController(BoardController boardController) {
        this.boardController = boardController;
        this.auction = new Auction();
    }

    public void startAuction() {
        auction.startAuction(boardController.getGame().getPlayers(), (Property) boardController.getGame().getCurrentSpace());
        updateCurrentBidderText();
        updateHighestOffer();
    }

    public void bidHigh() {
        auction.placeBigBid();
        updateCurrentBidderText();
        updateHighestOffer();
    }

    public void bidSmall() {
        auction.placeSmallBid();
        updateCurrentBidderText();
        updateHighestOffer();
    }

    public void dropOut() {
        auction.dropOutOfAuction(auction.getCurrentBidder());
        updateCurrentBidderText();
        endAuction(isAuctionOver());
    }

    private void endAuction(boolean auctionIsOver) {
        if (auctionIsOver) {
            boardController.clearBoardFlowPane();
            boardController.newPropertyOwner(auction.getAuctionProperty(), auction.getHighestBidder());
        }
    }

    private boolean isAuctionOver() {
        return !auction.isAuctionOngoing();
    }

    private void updateCurrentBidderText() {
        currentBidder.setText(auction.getCurrentBidder().getName());
    }

    private void updateHighestOffer() {
        highestOffer.setText(auction.getHighestOffer() + "kr");
    }

    /**
     * setFlowPane is called externally to add a child auctionFlowPane.
     *
     * @param anchorPane is to be added to the auctionFlowPane
     */
    public void setFlowPane(AnchorPane anchorPane) {
        clearFlowPane();
        addToFlowPane(anchorPane);
    }

    private void clearFlowPane() {
        auctionFlowPane.getChildren().clear();
    }

    private void addToFlowPane(AnchorPane anchorPane) {
        auctionFlowPane.getChildren().add(anchorPane);
    }
}
