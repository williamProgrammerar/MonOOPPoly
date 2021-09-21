package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Space> spaceList;

    public Board() {
        List<Space> tmpList = new ArrayList<>();
        tmpList.add(new Space("START",true));
        tmpList.add(new Space("CHANS", false));
        tmpList.add(new Space("DELTA PARKEN", false));
        this.spaceList = tmpList;
    }

    public Space getSpace(int i) {
        return spaceList.get(i);
    }
}
