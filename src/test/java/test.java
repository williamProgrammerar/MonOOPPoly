import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    List<String> list = new ArrayList<>();

    @BeforeEach
    void setUp() {
        list.add("William");
        list.add("Hampus");
        list.add("Jon");
        list.add("Vilhelm");
    }

    @Test
    void testChangePlayer() {
        for (int r = 0; r < 10; r++) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            String tmp = list.get(0);
            list.remove(0);
            list.add(tmp);
            System.out.println("");
        }
    }
}