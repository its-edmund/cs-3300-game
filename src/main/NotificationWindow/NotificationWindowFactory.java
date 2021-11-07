package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import window.gameboard.GameStateController;

public class NotificationWindowFactory {

    GameStateController gameStateController;
    ViewHandler viewHandler;

    public NotificationWindowFactory(GameStateController gameStateController, ViewHandler viewHandler) {
        this.gameStateController = gameStateController;
        this.viewHandler = viewHandler;
    }

    public AbstractNotification createNotification(GameStates notificationType) {

        AbstractNotification notification = null;

        if (notificationType == GameStates.CHANCE_NOTIFICATION) {
            notification = new ChanceCardNotification(gameStateController, viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);

        } else if (notificationType == GameStates.WALL_NOTIFICATION) {
            notification = new WallNotification(gameStateController, viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);

        } else if (notificationType == GameStates.VICTORY_NOTIFICATION) {
            notification = new VictoryNotification(viewHandler);
            notification.setPosX(0.5);
            notification.setPosY(0.5);
        }

        return notification;
    }

}
