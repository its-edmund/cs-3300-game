package M4;

import NotificationWindow.WallNotification;
import org.junit.Test;
import tile.Tile;
import tile.WallOrientationEnum;
import window.gameboard.GameStateController;
import window.gameboard.GameboardController;

import static junit.framework.TestCase.assertEquals;

public class TestHasWall {

    @Test
    public void testHasWall() {
        GameboardController gameboardController = new GameboardController(null);
        Tile tile = new Tile(0, 0, gameboardController);

        tile.addWall(WallOrientationEnum.TOP);

        assertEquals(tile.hasWall(), true);
    }
}
