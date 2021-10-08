package ModelTests;

import Model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StationTest {
   private final Station station = new Station("Station",200, 100, new int[] {25, 50, 100, 200});

   @BeforeEach
   void setHousesOwned() {
       station.setNrOfStationsOwned(4);
   }

   @Test
   void getRentTest() {
        assertEquals(station.getRent(), 200);
   }
}
