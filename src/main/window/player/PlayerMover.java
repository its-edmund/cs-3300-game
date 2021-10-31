package window.player;

import NotificationWindow.ButtonDecision;
import NotificationWindow.WallNotification;
import core.AbstractMoveMediator;
import core.PostMoveActionType;
import javafx.scene.layout.Pane;
import tile.Tile;
import tile.TileType;
import window.gameboard.GameboardController;

public class PlayerMover extends AbstractMoveMediator {

    private int remainingMoves;

    public PlayerMover(Player player, GameboardController gameboardController) {
        super(player, gameboardController);
    }

    @Override
    public PostMoveActionType movePlayer(int moveAmount) {
        int i = gameboardController.moveToken(player.getToken(), moveAmount);

        PostMoveActionType postMoveActionType = PostMoveActionType.NORMAL;

        if (i == 0) {
//            System.out.println("Movement succeeded.");

            TileType tileType = gameboardController.getTileTokenOccupies(player.getToken()).getType();

            if (tileType == TileType.CHANCE) {
                postMoveActionType = PostMoveActionType.CHANCE;
            } else if (tileType == TileType.GAIN_MONEY) {
                player.setMoney(player.getMoney() + 100);
                postMoveActionType = PostMoveActionType.NORMAL;
            } else if (tileType == TileType.LOSE_MONEY) {
                player.setMoney(player.getMoney() - 100);
                postMoveActionType = PostMoveActionType.NORMAL;
            } else if (tileType == TileType.END) {
                postMoveActionType = PostMoveActionType.VICTORY;
            }
        } else {
//            System.out.println("Movement blocked.");
            remainingMoves = i;

//            WallNotification wallNotification = new WallNotification(this);
//            Pane board = gameboardController.getBoard();
//            board.getChildren().addAll(wallNotification);
//            gameboardController.repositionChild(0.5, 0.5, wallNotification);

            postMoveActionType = PostMoveActionType.WALL;
        }

        return postMoveActionType;
    }

    public PostMoveActionType resumeMove() {
        PostMoveActionType postMoveActionType = movePlayer(remainingMoves);
        remainingMoves = 0;
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
