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
        tmpList.add(new Space("TENTAVAKT"));
        tmpList.add(new Locale("A-DAMMEN", 140, 70, "RED", new int[] {10, 50, 150, 450, 625, 750}, 100));
        tmpList.add(new Utility("Utility1", 150, 75));
        tmpList.add(new Locale("RÖDA RUMMET", 140, 70, "RED", new int[] {10, 50, 150, 450, 625, 750}, 100));
        tmpList.add(new Locale("HALVA SB-HUSET", 160, 80, "RED", new int[] {12, 60, 180, 500, 700, 900}, 100));
        tmpList.add(new Station("Station2", 200, 100));
        tmpList.add(new Locale("ELEKTROINNERGÅRDEN", 180, 90, "YELLOW", new int[] {14, 70, 200, 550, 750, 950}, 100));
        tmpList.add(new Chance());
        tmpList.add(new Locale("ANKETORG", 180, 90, "YELLOW", new int[] {14, 70, 200, 550, 750, 950}, 100));
        tmpList.add(new Locale("KAJSABAREN", 200, 100, "YELLOW", new int[] {16, 80, 220, 600, 800, 1000}, 100));
        tmpList.add(new Space("SJÄLVSTUDIER"));
        tmpList.add(new Locale("KATAKOMBERNA", 220, 110, "GREEN", new int[] {18, 90, 250, 700, 875, 1050}, 150));
        tmpList.add(new Chance());
        tmpList.add(new Locale("KEMIGÅRDEN", 220, 110, "GREEN", new int[] {18, 90, 250, 700, 875, 1050}, 150));
        tmpList.add(new Locale("FORT NOX", 240, 120, "GREEN", new int[] {20, 100, 300, 750, 925, 1100}, 150));
        tmpList.add(new Station("Station3", 200, 100));
        tmpList.add(new Locale("DALTONZ BURGARTÄLT", 260, 130, "GREY", new int[] {22, 110, 330, 800, 975, 1150}, 150));
        tmpList.add(new Locale("KÅRHUSET", 260, 130, "GREY", new int[] {22, 110, 330, 800, 975, 1150}, 150));
        tmpList.add(new Utility("Utility2", 150, 75));
        tmpList.add(new Locale("ZALOONEN", 280, 140, "GREY", new int[] {24, 120, 360, 850, 1025, 1200}, 150));
        tmpList.add(new Space("U"));
        tmpList.add(new Locale("BONDGATAN", 300, 150, "PURPLE", new int[] {26, 130, 390, 900, 1100, 1275}, 200));
        tmpList.add(new Chance());
        tmpList.add(new Locale("VASA-A", 300, 150, "PURPLE", new int[] {26, 130, 390, 900, 1100, 1275}, 200));
        tmpList.add(new Locale("GOLDEN I", 320, 160, "PURPLE", new int[] {28, 150, 450, 1000, 1200, 1400}, 200));
        tmpList.add(new Station("Station4", 200, 100));
        tmpList.add(new Chance());
        tmpList.add(new Locale("THE CLOUD", 350, 175, "TURQUOISE", new int[] {35, 175, 500, 1100, 1300, 1500}, 200));
        tmpList.add(new Tax(100));
        tmpList.add(new Locale("HUBBEN 2.1", 400, 200, "TURQUOISE", new int[] {50, 200, 600, 1400, 1700, 2000}, 200));
        this.spaceList = tmpList;
    }

    public Space getSpace(int i) {
        return spaceList.get(i);
    }

    public List<Space> getSpaceList() {
        return spaceList;
    }
}
