package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Auction.
 *
 * @author williamProgrammerar
 */
public class Auction {

    private boolean isAuctionOngoing = false;

    private final List<Player> bidders = new ArrayList<>();

    private Property auctionProperty;

    private int highestOffer = 0;

    private Player highestBidder;

    /**
     * startAuction has to be called in order to start an auction.
     * The if-statement makes sure there is no other auction ongoing.
     *
     * highestBidder is set as null to prevent the most recent auction winner from incorrectly receiving the property.
     *
     * @param players list of players who might participate in the auction.
     * @param property the property that will be up auction.
     */
    public void startAuction(List<Player> players, Property property) {
        if(!isAuctionOngoing) {
            initBidders(players);
            initAuctionProperty(property);
            resetHighestOffer();
            highestBidder = null;
            auctionOngoing(true);
        }
    }

    /**
     * initBidders places eligible players in the list bidders.
     *
     * @param players players that might participate in the auction.
     */
    private void initBidders(List<Player> players) {
        bidders.clear();
        for (Player player : players) {
            if (!player.isBankrupt()) {
                bidders.add(player);
            }
        }
    }

    private void initAuctionProperty(Property property) {
        auctionProperty = property;
    }

    private void resetHighestOffer() {
        highestOffer = 0;
    }

    private void auctionOngoing(boolean bool) {
        isAuctionOngoing = bool;
    }

    /**
     * Called by AuctionController to place a small bid
     */
    public void placeSmallBid() {
        int smallBid = 10;
        placeBid(smallBid);
    }

    /**
     * Called by AuctionController to place a big bid
     */
    public void placeBigBid() {
        int bigBid = 100;
        placeBid(bigBid);
    }

    /**
     * placeBid attempts to place a bid greater than the previous highestOffer.
     * Before placing a bid and allowing the next bidder to bid,
     * it is first checked if the current bidder can afford the bid they attempt to place.
     *
     * @param amount the amount of money to increase the highestOffer with.
     */
    private void placeBid(int amount) {
        if (isAuctionOngoing) {
            Player currentBidder = bidders.get(0);
            if (currentBidder.getCapital() >= (highestOffer + amount)) {
                highestBidder = currentBidder;
                highestOffer += amount;
                nextBidder();
            }
        }
    }

    /**
     * nextBidder places the previous bidder (index 0) at the last position in in list bidders.
     */
    private void nextBidder() {
        Player tmp = bidders.get(0);
        bidders.remove(0);
        bidders.add(tmp);
    }

    /**
     * dropOutOfAuction removes player from the list of bidders, then checks if the auction should end.
     *
     * @param player player to be removed from the list bidders
     */
    public void dropOutOfAuction(Player player) {
        bidders.remove(player);
        checkIfShouldEndAuction();
    }

    /**
     * endAuction ends the auction and transfers auctionProperty to highestBidder.
     *
     * if-statement should only be try if all but one bidder dropped out of the auction without bidding even once.
     */
    private void endAuction() {
        if (highestBidder == null) {
            highestBidder = bidders.get(0);
        }
        highestBidder.setCapital(highestBidder.getCapital() - highestOffer);
        highestBidder.getProperties().add(auctionProperty);
        auctionProperty.setOwned(true);
        auctionProperty.setOwnerId(highestBidder.getPlayerId());
        auctionOngoing(false);
    }

    /**
     * checkIfShouldEndAuction ends auction if there is only one bidder left.
     */
    private void checkIfShouldEndAuction() {
        if (bidders.size() == 1) {
            endAuction();
        }
    }

    public Player getCurrentBidder() {
        return bidders.get(0);
    }

    public int getHighestOffer() {
        return highestOffer;
    }

    public boolean isAuctionOngoing() {
        return isAuctionOngoing;
    }

    public Player getHighestBidder() {
        return highestBidder;
    }

    public Property getAuctionProperty() {
        return auctionProperty;
    }
}
