package model;

/**
 * An interface for every chance card there exists to extend.
 *
 * @author rhedinh
 */
public interface IChanceCard {
    //private final String text;

    //public IChanceCard(String text) {
    //    this.text = text;
    //}

    /**
     * Returns the text that will be displayed on the chance cad by the reader/player.
     *
     * @return the text (String) which will be read on the chance card
     */
    //public String getText(){ return text;}
    String getText();


    /**
     * Makes sure that whatever action the chance card says should happen, will happen.
     */
    void doAction(Player player);
}
