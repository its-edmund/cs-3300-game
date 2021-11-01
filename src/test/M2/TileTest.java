package M2;

import core.AppViewHandler;
import tile.Tile;
import tile.TileType;
import org.junit.Test;
import window.gameboard.GameboardController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TileTest {
    /**
     * Test Various tile creation constructors and rectangle representation
     */
    @Test
    public void createTileTest() {
        AppViewHandler viewHandler = null;
        GameboardController gameboardController = new GameboardController(viewHandler);
        Tile Standard = new Tile(0, 0, gameboardController);
        assertEquals(Standard.getType(), TileType.STANDARD);
        Tile NonStandard = new Tile(TileType.START, 50, 50, 0, 0, gameboardController);
        assertEquals(NonStandard.getType(), TileType.START);

        // Rectangle reprsentation tests
//        assertEquals(Standard.getTranslateX(), 0, 1);
//        assertEquals(NonStandard.getTranslateX(), 0, 1);

        assertEquals(12, Standard.getRectangle().getHeight(), 1);
        assertEquals(50, NonStandard.getRectangle().getHeight(), 1);
    }
}
