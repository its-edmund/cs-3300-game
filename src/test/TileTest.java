import tile.Tile;
import tile.TileType;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TileTest {
    /**
     * Test Various tile creation constructors and rectangle representation
     */
    @Test
    public void createTileTest() {
        Tile Standard = new Tile(0, 0);
        assertEquals(Standard.getType(), TileType.STANDARD);
        Tile NonStandard = new Tile(TileType.START, 0, 0);
        assertEquals(NonStandard.getType(), TileType.START);

        // Rectangle reprsentation tests
        assertEquals(Standard.getX(), 0, 1);
        assertEquals(NonStandard.getX(), 0, 1);

        assertEquals(Standard.getHeight(), 50, 1);
        assertEquals(NonStandard.getHeight(), 50, 1);
    }
}
