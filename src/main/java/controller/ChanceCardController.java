package controller;

import model.ChanceCardCreator;
import model.IChanceCard;

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
