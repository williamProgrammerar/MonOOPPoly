package model;

/**
 * A chance card which moves player to another space.
 *
 * @author rhedinh
 */
public class MoveChanceCard implements IChanceCard {
    private final String text;

    public MoveChanceCard(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    /**
     * Makes sure that whatever action the chance card says should happen, will happen.
     */
    @Override
    public void doAction(Player player) {

    }
}
