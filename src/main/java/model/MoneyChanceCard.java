package model;

/**
 * A chance card which gives or takes money from the player
 *
 * @author rhedinh
 */
public class MoneyChanceCard implements IChanceCard {
    private final int money;
    private final String text;

    public MoneyChanceCard(String text, int money) {
        this.text = text;
        this.money = money;
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
        player.setCapital(player.getCapital() + money);
        System.out.println("HEJ");
    }
}
