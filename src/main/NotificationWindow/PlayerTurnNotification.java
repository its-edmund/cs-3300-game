package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.paint.Color;
import window.player.Player;

public class PlayerTurnNotification extends AbstractTimedNotification {

    public PlayerTurnNotification(ViewHandler viewHandler) {
        super(viewHandler, 1.5);

        notificationBox.setWidth(150);
        notificationBox.setHeight(75);

        Player currentPlayer
                = viewHandler.getState().getPlayerController().getCurrentPlayer();

        notificationText.setText(currentPlayer.getName() + ", your turn!");

        notificationBox.setFill(currentPlayer.getToken().getFill());

        if (currentPlayer.getToken().getFill() == Color.BLACK) {
            notificationText.setFill(Color.WHITE);
        }

    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.GAMEBOARD_IDLE);
    }
}
