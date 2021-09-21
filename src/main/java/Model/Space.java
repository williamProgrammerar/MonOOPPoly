package Model;

public class Space {
    private final String spaceName;
    private final boolean isStart;

    public Space(String spaceName, boolean isStart) {
        this.spaceName = spaceName;
        this.isStart = isStart;
    }

    public String getSpaceName() {
        return spaceName;
    }
}
