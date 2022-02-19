package model;

public class Tax implements Space {
    private final int tax;
    private final String spaceName;

    public Tax(String spaceName, int tax) {
        this.spaceName = spaceName;
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }

    @Override
    public String getSpaceName() {
        return spaceName;
    }
}
