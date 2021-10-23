package model;

public class Tax extends Space {
    private final int tax;

    public Tax(String spaceName, int tax) {
        super(spaceName);
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
}
