package window.gameboard;

import NotificationWindow.*;
import core.GameStates;
import core.ViewHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import window.player.Player;
import core.PostMoveActionType;

import java.io.IOException;

public class GameStateController {

    protected ViewHandler viewHandler;
    private GameboardController gameboardController;

    private int playerTurnIndex;

//    private WallNotification wallNotification;
//    private ChanceCardNotification chanceCard;
//    private VictoryNotification victoryNotification;
//    private AbstractNotificationWindow notification;

    private AbstractNotification currentNotification;

    private NotificationWindowFactory notificationWindowFactory;

    public GameStateController(ViewHandler viewHandler, GameboardController gameboardController) {
        this.viewHandler = viewHandler;
        this.gameboardController = gameboardController;
        playerTurnIndex = 0;

        notificationWindowFactory = new NotificationWindowFactory(this, viewHandler);

        viewHandler.getState().updateState(GameStates.MASTERPIECE_RULES);

        Timeline manageGamestate = new Timeline(new KeyFrame(Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        if (viewHandler.getState().stateChangeOccurred()) {

                            Runnable prevStateTask = null;

                            // Cleanup from the previous state
                            switch (viewHandler.getState().getPrevState()) {
                                // If we exited from a notification,
                                // remove the notification
                                case CHANCE_NOTIFICATION:
                                case VICTORY_NOTIFICATION:
                                case WALL_NOTIFICATION:
                                case EXAMPLE_NOTIFICATION:
                                case NEW_TURN:
                                case MASTERPIECE_RULES:
                                    prevStateTask = () -> {
                                        viewHandler.getState().removeNotification();
                                    };
                                    break;
                                case END_TURN:

//                                    prevStateTask = () -> {
//                                        gameboardController.changePlayerStatus(playerTurnIndex);
//                                    };
//                                    break;
                            }

                            Runnable currStateTask = null;

                            switch (viewHandler.getState().getCurrentState()) {
                                case MOVING:
                                    currStateTask = () -> {
                                        handleCurrentPlayerMovement2();
                                    };
                                    break;
                                case END_TURN:
                                    currStateTask = () -> {
                                        endPlayerMove();
                                    };
                                    break;
                                case CHANCE_NOTIFICATION:

                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.CHANCE_NOTIFICATION)
                                        );
                                    };
                                    break;
                                case WALL_NOTIFICATION:

                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.WALL_NOTIFICATION)
                                        );
                                    };
                                    break;
                                case VICTORY_NOTIFICATION:

                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.VICTORY_NOTIFICATION)
                                        );
                                    };
                                    break;
                                case EXAMPLE_NOTIFICATION:
                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.EXAMPLE_NOTIFICATION)
                                        );
                                    };
                                    break;
                                case GAME_OVER:

                                    currStateTask = () -> {
                                        try {
                                            viewHandler.launchVictoryScreen();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    };
                                    break;
                                case GAMEBOARD_IDLE:

                                    break;
                                case NEW_TURN:
                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.NEW_TURN)
                                        );
                                    };
                                    break;
                                case MASTERPIECE_RULES:
                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.MASTERPIECE_RULES)
                                        );
                                    };
                                    break;
                            }

                            // If we are not immediately leaving the current state,
                            // update previous state
                            viewHandler.getState().updateState();

                            if (prevStateTask != null) {
                                Platform.runLater(prevStateTask);
                            }
                            if (currStateTask != null) {
                                Platform.runLater(currStateTask);
                            }
                        }
                    }


                })

        );
        manageGamestate.setCycleCount(Timeline.INDEFINITE);
        manageGamestate.play();
    }

    public void handleCurrentPlayerMovement(int movement) {

        if (viewHandler.getState().getCurrentState() == GameStates.MOVING) {
            Player movingPlayer = getMovingPlayer();
            PostMoveActionType action = movingPlayer.getPlayerMover().movePlayer(movement);

            handlePostMoveAction(action);
        }
    }

    public void handleCurrentPlayerMovement2() {

        Player movingPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();
        PostMoveActionType action = movingPlayer.getPlayerMover().movePlayer2();
        handlePostMoveAction(action);
    }

//    public void resumePlayerMoveAfterWallRemoved() {
//        viewHandler.getState().setCurrentState(GameStates.MOVING);
//
//        Player movingPlayer = getMovingPlayer();
//        PostMoveActionType action = movingPlayer.getPlayerMover().resumeMove();
//
//        handlePostMoveAction(action);
//    }

    public void endPlayerMove() {
        viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(0);
        viewHandler.getState().getPlayerController().endCurrentPlayerTurn();
        viewHandler.getState().updateState(GameStates.NEW_TURN);
    }

    // Helper Functions
    private void handlePostMoveAction(PostMoveActionType action) {

        switch (action) {
            case CHANCE:
                viewHandler.getState().updateState(GameStates.CHANCE_NOTIFICATION);
                break;
            case WALL:
                viewHandler.getState().updateState(GameStates.WALL_NOTIFICATION);
                break;
            case VICTORY:
                viewHandler.getState().updateState(GameStates.VICTORY_NOTIFICATION);
                break;
            case EXAMPLE_NOTIFICATION:
                viewHandler.getState().updateState(GameStates.EXAMPLE_NOTIFICATION);
                break;
            default:
                viewHandler.getState().updateState(GameStates.END_TURN);
                break;
        }
    }

    // Getters and Setters
    public Player getMovingPlayer() {
        return viewHandler.getState().getPlayerController().get(playerTurnIndex);
    }
}
