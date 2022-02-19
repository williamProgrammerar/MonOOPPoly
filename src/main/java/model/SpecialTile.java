package model;

public class SpecialTile implements Space {
    String spaceName;

    public SpecialTile(String spaceName) {
        this.spaceName = spaceName;
    }

    @Override
    public String getSpaceName() {
        return spaceName;
    }
}
