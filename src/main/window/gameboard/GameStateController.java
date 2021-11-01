package window.gameboard;

import NotificationWindow.*;
import NotificationWindow.ChanceCard.NewChanceCard;
import core.GameStates;
import core.ViewHandler;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import window.player.Player;
import core.PostMoveActionType;

public class GameStateController {

    protected ViewHandler viewHandler;
    private GameboardController gameboardController;

    private int playerTurnIndex;

    private WallNotification wallNotification;
    private NewChanceCard chanceCard;
    private VictoryNotification victoryNotification;
    private AbstractNotificationWindow notification;

    public GameStateController(ViewHandler viewHandler, GameboardController gameboardController) {
        this.viewHandler = viewHandler;
        this.gameboardController = gameboardController;
        playerTurnIndex = 0;

        if (viewHandler != null) {
            viewHandler.getState().setCurrentState(GameStates.MOVING);
        }
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

            chanceCard = new NewChanceCard(this);
            chanceCard.setPosX(0.5);
            chanceCard.setPosY(0.5);
            gameboardController.getBoard().getChildren().addAll(chanceCard);

            viewHandler.getState().setCurrentState(GameStates.WAITING_FOR_RESPONSE);

        } else if (action == PostMoveActionType.WALL) {

            wallNotification = new WallNotification(this);
            wallNotification.setPosX(0.5);
            wallNotification.setPosY(0.5);
            gameboardController.getBoard().getChildren().addAll(wallNotification);

            viewHandler.getState().setCurrentState(GameStates.WAITING_FOR_RESPONSE);

        } else if (action == PostMoveActionType.VICTORY) {
            System.out.println("Game over!");

            victoryNotification = new VictoryNotification(viewHandler);
            victoryNotification.setPosX(0.5);
            victoryNotification.setPosY(0.5);
            gameboardController.getBoard().getChildren().addAll(victoryNotification);

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

    // Getters and Setters
    public Player getMovingPlayer() {
        return viewHandler.getState().getPlayerController().get(playerTurnIndex);
    }
    public GameStates getCurrentGamestate() {
        return viewHandler.getState().getCurrentState();
    }
    public void setCurrentGamestate(GameStates currentState) {
        viewHandler.getState().setCurrentState(currentState);
    }
}
