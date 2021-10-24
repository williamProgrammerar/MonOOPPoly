package model;

/**
 * An abstract class for every chance card there exists to extend.
 *
 * @author rhedinh
 */
public abstract class IChanceCard {
    private final String text;

    public IChanceCard(String text) {
        this.text = text;
    }

    /**
     * Returns the text that will be displayed on the chance cad by the reader/player.
     *
     * @return the text (String) which will be read on the chance card
     */
    public String getText(){
        return text;
    }

    /**
     * Makes sure that whatever action the chance card says should happen, will happen.
     */
    public abstract void doAction(Player player);
}
