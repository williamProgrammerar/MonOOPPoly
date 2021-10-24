package controller;

import model.Auction;
import model.Game;
import model.Player;
import model.Property;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author williamProgrammerar
 */
public class AuctionController {
    private Game game;
    @FXML
    private FlowPane auctionFlowPane;

    @FXML
    private Text highestOffer;

    @FXML
    private Text currentBidder;


    private Auction auction;

    /**
     * Constructor that when called gives AuctionController access to existing boardController.
     *
     * @param game the existing game.
     */
    public AuctionController(Game game) {
        this.game = game;
    }

    /**
     * startAuction is public so that when the button "Auction" is pressed boardController can start the auctioning process.
     */
    public void startAuction() {
        game.startAuction();
        this.auction = game.getAuction();
        updateCurrentBidderText();
        updateHighestOffer();
    }

    /**
     * bidHigh is public so that a button can access this method and place a big bid.
     */
    public void bidHigh() {
        auction.placeBigBid();
        updateCurrentBidderText();
        updateHighestOffer();
    }

    /**
     * bidSmall is public so that a button can access this method and place a small bid.
     */
    public void bidSmall() {
        auction.placeSmallBid();
        updateCurrentBidderText();
        updateHighestOffer();
    }

    /**
     * dropOut is public so that a button can access this method and allow a player to drop out of the auction.
     */
    public void dropOut() {
        auction.dropOutOfAuction(auction.getCurrentBidder());
        updateCurrentBidderText();
        endAuction(isAuctionOver());
    }

    /**
     * endAuction if the auction is over will give the person who places the highest bid the auctioned property.
     *
     * @param auctionIsOver boolean that states whether or not the auction is over.
     */
    private void endAuction(boolean auctionIsOver) {
        if (auctionIsOver) {
            game.notifyObservers(auction);
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
