package Model;

/**
 *
 * @author rhedinh
 */
public class MoneyChanceCard implements IChanceCard {
    private int money;
    private String text;

    public MoneyChanceCard(int money, String text) {
        this.money = money;
        this.text = text;
    }
}
