package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class with all the chance card that exists in the game. When a new ChanceCardCreator
 * is made, it randomises and choses one chance card to return.
 *
 * @author rhedinh
 */

public class ChanceCardCreator {
    private final IChanceCard chanceCard;

    private static final List<IChanceCard> chanceCards = new ArrayList<>() {{
        add(new MoneyChanceCard("Du måste betala kåravgift, betala 240 kr till kåren.", -240));
        add(new MoneyChanceCard("Tack för att du deltog i kursutvärderingen, inkassera 100 kr.", 100));
    }};

    /**
     * Randomises a chance card
     */
    public ChanceCardCreator() {
        final Random rand = new Random();
        final int cardNumber = rand.nextInt(chanceCards.size());
        this.chanceCard = chanceCards.get(cardNumber);
    }

    public IChanceCard getChanceCard() {
        return chanceCard;
    }
}
