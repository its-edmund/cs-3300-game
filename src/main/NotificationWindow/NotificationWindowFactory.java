package NotificationWindow;

import Minigame.AbstractMinigameController;
import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.gameboard.GameStateController;
import window.player.Award;
import window.player.Player;
import window.player.PlayerController;

public class NotificationWindowFactory {

    ViewHandler viewHandler;

    public NotificationWindowFactory(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public AbstractNotification createNotification(GameStates notificationType) {

        AbstractNotification notification = null;

        AbstractMinigameController minigameController =
                viewHandler.getState().getCurrentMinigame();

        if (notificationType == GameStates.CHANCE_NOTIFICATION) {
            notification = new ChanceCardNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);

        } else if (notificationType == GameStates.WALL_NOTIFICATION) {
            notification = new WallNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);

        } else if (notificationType == GameStates.VICTORY_NOTIFICATION) {
            notification = new VictoryNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);
        } else if (notificationType == GameStates.NEW_TURN) {
            notification = new PlayerTurnNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.4);
        } else if (notificationType == GameStates.MASTERPIECE_RULES) {
            notification = new GameRulesNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.4);
        } else if (notificationType == GameStates.MINIGAME_PLAY_OVER) {
            notification = new PlayerMinigameScoreNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);
        } else if (notificationType == GameStates.MINIGAME_INDIVIDUAL_SCORE) {
            notification = new PlayerMinigameScoreNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);
        } else if (notificationType == GameStates.MINIGAME_INSTRUCTIONS) {

            notification = new MinigameInstructionsNotification(viewHandler);

//            notification = createGenericButtonNotification(
//                    new Label(minigameController.getMinigameTitle()),
//                    new Label(minigameController.getMinigameDescription()),
//                    "OK, Got it!",
//                    GameStates.MINIGAME_PLAYER_READY,
//                    250, 150
//            );
//            notification.setPosX(0.5);
//            notification.setPosY(0.5);
        } else if (notificationType == GameStates.MINIGAME_PLAYER_READY) {

            PlayerController playerController = viewHandler.getState().getPlayerController();

            Label desc = new Label(playerController.getCurrentMinigamePlayer().getName()
                    + ", are you ready?");
            desc.setTextFill(Color.BLACK);

            notification = createGenericButtonNotification(
                    null,
                    desc,
                    "Ready!",
                    GameStates.MINIGAME_PLAY,
                    150, 120
            );
            notification.setPosX(0.5);
            notification.setPosY(0.5);
            notification.setNotificationColor(viewHandler.getState().getPlayerController().getCurrentMinigamePlayer().getColor());
        } else if (notificationType == GameStates.MINIGAME_RESULTS) {

            notification = new MinigameSummaryNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);

        } else if (notificationType == GameStates.VICTORY_SCREEN) {
            notification = new VictoryScreen(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);
        }

        return notification;
    }

    public AbstractNotification createAwardNotification(Award award) {

        return new AwardNotification(viewHandler, award);
    }

    public GenericButtonNotification createGenericButtonNotification(Label title, Label desc, String buttonDesc,
                                                               GameStates exitState, double width, double height) {
        if (desc == null)
            desc = new Label("");
        if (buttonDesc == null)
            buttonDesc = "";

        GenericButtonNotification notification =
                new GenericButtonNotification(viewHandler, exitState);

        // Title label
        if (title != null) {
            title.setFont(new Font(14));

            // Add text to the notification
            notification.setNotificationText(
                    title,
                    desc
            );
        } else {
            notification.setNotificationText(
                    desc
            );
        }

        // Set text of the button
        notification.getButton(0).setText(buttonDesc);

        // Set the width and height of the notification
        notification.setNotificationWidth(width);
        notification.setNotificationHeight(height);

        // Reposition the notification
        notification.setPosX(0.5);
        notification.setPosY(0.5);

        return notification;
    }

}
