package Model;

public class Tax extends Space {

    private final int tax;

    public Tax(int tax) {
        super("KÅRAVGIFT");
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
}
