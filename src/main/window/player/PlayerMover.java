package window.player;

import core.AbstractMoveMediator;
import tile.Tile;
import tile.TileType;
import window.gameboard.GameboardController;

public class PlayerMover extends AbstractMoveMediator {

    public PlayerMover(GameboardController gameboardController) {
        super(gameboardController);
    }

    @Override
    public boolean movePlayer(Player player, int moveAmount) {
        gameboardController.moveToken(player.getToken(), moveAmount);

        TileType tileType = gameboardController.getTileTokenOccupies(player.getToken()).getType();

        if (tileType == TileType.CHANCE) {
            return true;
        } else if (tileType == TileType.GAIN_MONEY) {
            player.setMoney(player.getMoney() + 100);
        } else if (tileType == TileType.LOSE_MONEY) {
            player.setMoney(player.getMoney() - 100);
        } else if (tileType == TileType.END) {

        }

        return false;

    }
    // For M3 Tests
    public int setTile(Tile tile, Player player) {
        TileType tileType = tile.getType();
        if (tileType  == TileType.GAIN_MONEY) {
            player.setMoney(player.getMoney() + 100);
        } else if (tileType == TileType.LOSE_MONEY) {
            player.setMoney(player.getMoney() - 100);
        }
        return player.getMoney();
    }
}
