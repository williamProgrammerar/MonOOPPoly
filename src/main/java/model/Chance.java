package model;

public class Chance implements Space {
    String spaceName;

    public Chance(String spaceName){
    this.spaceName = spaceName;
    }

    @Override
    public String getSpaceName() {
        return spaceName;
    }

}
