package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Space> spaceList;

    public Board() {
        List<Space> tmpList = new ArrayList<>();
        tmpList.add(new Space("GO"));
        tmpList.add(new Locale("BASEN", 60, 30, "ORANGE", new int[] {2, 10, 30, 90, 160, 250}, 50));
        tmpList.add(new Chance());
        tmpList.add(new Locale("DELTA PARKEN", 60, 30, "ORANGE", new int[] {4, 20, 60, 180, 320, 450}, 50));
        tmpList.add(new Tax(200));
        tmpList.add(new Station("Station1", 200, 100));
        tmpList.add(new Locale("WINDEN", 100, 50, "BROWN", new int[] {6, 30, 90, 270, 400, 550}, 100));
        tmpList.add(new Chance());
        tmpList.add(new Locale("XP-LABBET", 100, 50, "BROWN", new int[] {6, 30, 90, 270, 400, 550}, 100));
        tmpList.add(new Locale("BULTEN", 120, 60, "BROWN", new int[] {8, 40, 100, 300, 450, 600}, 100));
        tmpList.add(new Space("OMTENTA"));
        //TODO ADD REST OF THE SPACES
        this.spaceList = tmpList;
    }

    public Space getSpace(int i) {
        return spaceList.get(i);
    }
}
