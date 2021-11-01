package M4;

import org.junit.Test;
import tile.Tile;
import tile.WallOrientationEnum;
import window.gameboard.GameboardController;

import static junit.framework.TestCase.assertEquals;

public class TestRemoveWall {
    @Test
    public void testHasWall() {
        GameboardController gameboardController = new GameboardController(null);
        Tile tile = new Tile(0, 0, gameboardController);

        tile.addWall(WallOrientationEnum.TOP);
        assertEquals(tile.hasWall(), true);
        tile.removeWall();
        assertEquals(tile.hasWall(), false);
    }
}
