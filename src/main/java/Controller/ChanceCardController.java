package Controller;

import Model.ChanceCardCreator;
import Model.IChanceCard;

/**
 * Controls the chance cards
 *
 * @author rhedinh
 */
public class ChanceCardController {
    private IChanceCard chanceCard;

    public ChanceCardController() {
        chanceCard = new ChanceCardCreator().getChanceCard();
    }
}
