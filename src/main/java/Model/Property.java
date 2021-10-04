package Model;

public class Property extends Space {

    private boolean isOwned;
    private boolean isMortgaged;
    private String ownerName;
    private final int price;
    private final int mortgage;
    private final int[] rent;

    public Property(String spaceName, int price, int mortgage, int[] rent) {
        super(spaceName);
        this.rent = rent;
        this.isOwned = false;
        this.isMortgaged = false;
        this.price = price;
        this.mortgage = mortgage;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getPrice() {
        return price;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getRent() {
        return rent[0];
    }
}
