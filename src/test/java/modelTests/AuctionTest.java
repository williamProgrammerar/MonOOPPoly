package modelTests;

import model.Auction;
import model.Player;
import model.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.HandleRentViewMapVisitor;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class AuctionTest {
    private final Auction auction = new Auction();
    private final List<Player> players = new ArrayList<>();
    private Property property;

    @BeforeEach
    void setPlayers() {
        players.add(new Player(1,1500));
        players.add(new Player(2,1500));
        players.add(new Player(3,1500));
        players.add(new Player(4,1500));
        property = new Property("Property", 200, 100, new int[] {1,2,3,4,5,6}) {
            @Override
            public void accept(HandleRentViewMapVisitor visitor) {
                System.out.println("accept");
            }
        };
    }

    @Test
    void testStartAuction() {
        auction.startAuction(players, property);
        assertNull(auction.getHighestBidder());
        assertSame(auction.getAuctionProperty(), property);
        assertEquals(auction.getHighestOffer(), 0);
        assertSame(auction.getCurrentBidder(), players.get(0));
        assertTrue(auction.isAuctionOngoing());
    }

    @Test
    void testPlaceBid() {
        auction.startAuction(players, property);
        auction.placeSmallBid();
        assertSame(auction.getHighestBidder(), players.get(0));

        auction.placeBigBid();
        assertSame(auction.getHighestBidder(), players.get(1));
    }

    @Test
    void testDropOut() {
        auction.startAuction(players, property);
        for (int i = 0; i < players.size() - 1; i++) {
            auction.dropOutOfAuction(auction.getCurrentBidder());
        }
        assertFalse(auction.isAuctionOngoing());
    }
}
