package model;

/**
 * A chance card which moves player to another space.
 *
 * @author rhedinh
 */
public class MoveChanceCard extends IChanceCard {

    public MoveChanceCard(String text) {
        super(text);
    }

    /**
     * Makes sure that whatever action the chance card says should happen, will happen.
     */
    @Override
    public void doAction(Player player) {

    }
}
