package Controller;

import Model.ChanceCardCreator;
import Model.IChanceCard;
import java.util.List;

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
