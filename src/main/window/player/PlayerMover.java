package window.player;

//import NotificationWindow.WallNotification;
import core.AbstractMoveMediator;
import core.PostMoveActionType;
import javafx.scene.layout.Pane;
import tile.Tile;
import tile.TileType;
import window.gameboard.GameboardController;

public class PlayerMover extends AbstractMoveMediator {

    private int remainingMoves;
    private int squaresTranversed;

    public PlayerMover(Player player, GameboardController gameboardController) {
        super(player, gameboardController);

        squaresTranversed = 0;
    }

    public PostMoveActionType movePlayer() {
        int i = gameboardController.moveToken(player.getToken(), remainingMoves);
        squaresTranversed += remainingMoves - i;

        PostMoveActionType postMoveActionType = PostMoveActionType.NORMAL;

        if (i == 0) {

            // Movement not blocked: see if we landed on a special tile
//            TileType tileType = gameboardController.getTileTokenOccupies(player.getToken()).getType();
            TileType tileType = player.getToken().getCurrentTile().getType();

            switch (tileType) {
                case CHANCE:
                    postMoveActionType = PostMoveActionType.CHANCE;
                    break;
                case GAIN_MONEY:
                    player.setMoney(player.getMoney() + 200);
                    postMoveActionType = PostMoveActionType.NORMAL;
                    break;
                case LOSE_MONEY:
                    player.setMoney(player.getMoney() - 100);
                    postMoveActionType = PostMoveActionType.NORMAL;
                    break;
                case END:

                    if (squaresTranversed >= 60) {
                        postMoveActionType = PostMoveActionType.VICTORY;
                    } else {
                        postMoveActionType = PostMoveActionType.NORMAL;
                    }

                    break;
                case LAUNCH_EXAMPLE_NOTIFICATION:
                    postMoveActionType = PostMoveActionType.EXAMPLE_NOTIFICATION;
                    break;
                case LAUNCH_MINIGAME:
                    postMoveActionType = PostMoveActionType.MINIGAME;
                    break;
            }
        } else {
            // Movement is blocked by a wall
            remainingMoves = i;
            postMoveActionType = PostMoveActionType.WALL;
        }

        return postMoveActionType;
    }

    // Get / Set remaining moves
    public int getRemainingMoves() {
        return remainingMoves;
    }
    public Tile getCurrentTile() {
        return gameboardController.getTile(player.getCurrentLocation());
    }
    public Tile getNextTile() {
        return gameboardController.getTile(player.getCurrentLocation() + 1);
    }

    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
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
