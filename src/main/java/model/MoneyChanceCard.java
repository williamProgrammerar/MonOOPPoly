package model;

/**
 * A chance card which gives or takes money from the player
 *
 * @author rhedinh
 */
public class MoneyChanceCard extends IChanceCard {
    private final int money;

    public MoneyChanceCard(String text, int money) {
        super(text);
        this.money = money;
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
