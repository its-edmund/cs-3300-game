package M4;

import NotificationWindow.WallNotification;
import javafx.scene.paint.Color;
import org.junit.Test;
import tile.Tile;
import tile.TileType;
import tile.Wall;
import window.gameboard.GameboardController;
import window.player.Player;

import static junit.framework.TestCase.assertEquals;

public class PlayerTileType {
    @Test
    public void testTileType() {
        Player p = new Player("placeholder", Color.BLUE, 100, null);
        GameboardController gameboardController = new GameboardController(null);
        gameboardController.initialize(null, null);
        p.getToken().setTokenLocation(0);
        Tile tile1 = gameboardController.getTile(1);
        assertEquals(tile1.getType(), TileType.GAIN_MONEY);
        Tile tile2 = gameboardController.getTile(2);
        assertEquals(tile2.getType(), TileType.LOSE_MONEY);
    }
}
