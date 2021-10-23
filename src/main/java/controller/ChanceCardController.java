package controller;

import model.ChanceCardCreator;
import model.IChanceCard;

/**
 * Controlls the chance cards
 *
 * @author rhedinh
 */

public class ChanceCardController {
    private IChanceCard chanceCard;

    public ChanceCardController() {
        chanceCard = new ChanceCardCreator().getChanceCard();
    }
}
