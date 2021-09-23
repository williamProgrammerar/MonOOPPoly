package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Space> spaceList;

    public Board() {
        List<Space> tmpList = new ArrayList<>();
        tmpList.add(new Locale("BASEN", 60, 30, "ORANGE", new int[] {2, 10, 30, 90, 160, 250}, 50));
        this.spaceList = tmpList;
    }

    public Space getSpace(int i) {
        return spaceList.get(i);
    }
}
