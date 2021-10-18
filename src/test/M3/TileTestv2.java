package M3;

import core.AppViewHandler;
import tile.Tile;
import tile.TileType;
import org.junit.Test;
import window.gameboard.GameboardController;
import window.player.Player;
import window.player.PlayerMover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TileTestv2 {
    /**
     * Test green and red tile functionality
     */
    @Test
    public void redTileTest() {
        AppViewHandler viewHandler = null;
        Tile loseMoneyTile = new Tile(TileType.LOSE_MONEY, 50, 50, 0, 0, viewHandler);
        GameboardController gController = new GameboardController(viewHandler);
        PlayerMover player = new PlayerMover(gController);
        Player p = new Player();
        p.setMoney(1000);
        assertEquals(player.setTile(loseMoneyTile, p), 900);
    }

    @Test
    public void greenTileTest() {
        AppViewHandler viewHandler = null;
        Tile loseMoneyTile = new Tile(TileType.GAIN_MONEY, 50, 50, 0, 0, viewHandler);
        GameboardController gController = new GameboardController(viewHandler);
        PlayerMover player = new PlayerMover(gController);
        Player p = new Player();
        p.setMoney(1000);
        assertEquals(player.setTile(loseMoneyTile, p), 1100);
    }
}
