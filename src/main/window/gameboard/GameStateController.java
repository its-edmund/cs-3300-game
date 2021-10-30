package window.gameboard;

import NotificationWindow.*;
import core.GameStates;
import core.ViewHandler;
import javafx.scene.layout.Pane;
import window.player.Player;
import core.PostMoveActionType;

public class GameStateController {

    protected ViewHandler viewHandler;
    private GameboardController gameboardController;

    private int playerTurnIndex;

    private WallNotification wallNotification;
    private AbstractNotificationWindow notification;

    public GameStateController(ViewHandler viewHandler, GameboardController gameboardController) {
        this.viewHandler = viewHandler;
        this.gameboardController = gameboardController;
        playerTurnIndex = 0;

        viewHandler.getState().setCurrentState(GameStates.MOVING);
    }

    public void handleDiceRoll(int movement) {

        if (viewHandler.getState().getCurrentState() == GameStates.MOVING) {
            Player movingPlayer = getMovingPlayer();
            PostMoveActionType action = movingPlayer.getPlayerMover().movePlayer(movement);

            handlePostMoveAction(action);
        }
    }

    public void resumePlayerMoveAfterWallRemoved() {
        viewHandler.getState().setCurrentState(GameStates.MOVING);

        Player movingPlayer = getMovingPlayer();
        PostMoveActionType action = movingPlayer.getPlayerMover().resumeMove();

        handlePostMoveAction(action);
    }

    public void endPlayerMove() {
        viewHandler.getState().setCurrentState(GameStates.MOVING);
        Player movingPlayer = getMovingPlayer();
        movingPlayer.getPlayerMover().setRemainingMoves(0);

        changePlayerTurn();
    }

    // Helper Functions
    private void handlePostMoveAction(PostMoveActionType action) {
        if (action == PostMoveActionType.CHANCE) {


            notification = (new NotificationWindowFactory())
                    .createNotificationWindow(NotificationScreenEnum.CHANCE_CARD, viewHandler);

            Pane board = gameboardController.getBoard();
            board.getChildren().addAll(notification.getParent());

//            gameboardController.repositionChild(0.5, 0.5);

//            changePlayerTurn();
        } else if (action == PostMoveActionType.WALL) {

            wallNotification = new WallNotification(this);
            Pane board = gameboardController.getBoard();
            board.getChildren().addAll(wallNotification);
            gameboardController.repositionChild(0.5, 0.5, wallNotification);

            viewHandler.getState().setCurrentState(GameStates.WAITING_FOR_RESPONSE);

        } else if (action == PostMoveActionType.VICTORY) {
            System.out.println("Game over!");
        } else {
            changePlayerTurn();
        }
    }
    private void changePlayerTurn() {
        if (playerTurnIndex == viewHandler.getState().getPlayerController().getPlayers().size() - 1) {
            playerTurnIndex = 0;
        } else {
            playerTurnIndex++;
        }

        gameboardController.changePlayerStatus(playerTurnIndex);
    }
    public Player getMovingPlayer() {
        return viewHandler.getState().getPlayerController().get(playerTurnIndex);
    }

}
