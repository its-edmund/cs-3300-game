package window.gameboard;

import Minigame.AbstractMinigame;
import Minigame.MinigameFactory;
import NotificationWindow.*;
import core.GameStates;
import core.ViewHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import window.player.Player;
import core.PostMoveActionType;
import window.player.PlayerController;

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
    private MinigameFactory minigameFactory;

    public GameStateController(ViewHandler viewHandler, GameboardController gameboardController) {
        this.viewHandler = viewHandler;
        this.gameboardController = gameboardController;
        playerTurnIndex = 0;

        notificationWindowFactory = new NotificationWindowFactory(this, viewHandler);
        minigameFactory = new MinigameFactory(viewHandler);

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
                                case MINIGAME_INSTRUCTIONS:
                                case MINIGAME_PLAYER_READY:
                                    prevStateTask = () -> {
                                        viewHandler.getState().removeNotification();
                                    };
                                    break;

                                case MINIGAME_RESULTS:
                                case END_TURN:
                                    prevStateTask = () -> {
                                        viewHandler.getState().removeMinigame();
                                    };
                                    break;
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
                                case MINIGAME_LAUNCH:
                                    currStateTask = () -> {
                                        viewHandler.getState().addMinigame(
                                            minigameFactory.createMinigame(GameStates.MINIGAME_LAUNCH)
                                        );
                                    };
                                    break;
                                case MINIGAME_INSTRUCTIONS:
                                    currStateTask = () -> {

                                        AbstractMinigame minigame = viewHandler.getState().getCurrentMinigame();

                                        viewHandler.getState().addNotification(
                                            notificationWindowFactory.createGenericButtonNotification(
                                                new Label(minigame.getMinigameTitle()),
                                                new Label(minigame.getMinigameDescription()),
                                                "OK, got it!",
                                                GameStates.MINIGAME_PLAYER_READY,
                                                250,150
                                            )
                                        );
                                    };
                                    break;
                                case MINIGAME_PLAYER_READY:
                                    currStateTask = () -> {

                                        PlayerController playerController = viewHandler.getState().getPlayerController();

                                        Label desc = new Label(playerController.getCurrentMinigamePlayer().getName()
                                                + ", are you ready?");
                                        desc.setTextFill(Color.BLACK);

                                        GenericButtonNotification notification =
                                                notificationWindowFactory.createGenericButtonNotification(
                                                        null,
                                                        desc,
                                                        "Ready!",
                                                        GameStates.MINIGAME_PLAY,
                                                        150,120
                                                );

                                        notification.setNotificationColor(
                                                playerController.getCurrentMinigamePlayer().getColor()
                                        );

                                        viewHandler.getState().addNotification(notification);
                                    };
                                    break;
                                case MINIGAME_PLAY_OVER:

                                    currStateTask = () -> {
                                        viewHandler.getState().addNotification(
                                                notificationWindowFactory.createNotification(GameStates.MINIGAME_PLAY_OVER)
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

//    public void handleCurrentPlayerMovement(int movement) {
//
//        if (viewHandler.getState().getCurrentState() == GameStates.MOVING) {
//            Player movingPlayer = getMovingPlayer();
//            PostMoveActionType action = movingPlayer.getPlayerMover().movePlayer(movement);
//
//            handlePostMoveAction(action);
//        }
//    }

    public void handleCurrentPlayerMovement2() {

        Player movingPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();
        PostMoveActionType action = movingPlayer.getPlayerMover().movePlayer();
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
            case MINIGAME:
                viewHandler.getState().updateState(GameStates.MINIGAME_LAUNCH);
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
