package M4;

import NotificationWindow.WallNotification;
import javafx.scene.paint.Color;
import org.junit.Test;
import tile.Tile;
import tile.TileType;
import window.gameboard.GameStateController;
import window.gameboard.GameboardController;
import window.player.Player;
import window.player.PlayerMover;

import static junit.framework.TestCase.assertEquals;

public class PlayerFinishTest {

    @Test
    public void playerFinishTest() {
        Player p = new Player("placeholder", Color.BLUE, 100, null);
        GameboardController gameboardController = new GameboardController(null);
        gameboardController.initialize(null, null);
        p.getToken().setTokenLocation(0);
        Tile tile = gameboardController.getTile(0);

        assertEquals(tile.getType(), TileType.END);
    }


}
