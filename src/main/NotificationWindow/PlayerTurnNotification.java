package NotificationWindow;

import core.AppPaths;
import core.GameStates;
import core.ViewHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import window.player.Player;

import java.io.File;
import java.net.URISyntaxException;

public class PlayerTurnNotification extends AbstractTimedNotification {

    public PlayerTurnNotification(ViewHandler viewHandler) {
        super(viewHandler, 1.5);

        notificationBox.setWidth(150);
        notificationBox.setHeight(75);

        Player currentPlayer
                = viewHandler.getState().getPlayerController().getCurrentPlayer();
        currentPlayer.playNewTurnSound();

        setNotificationText(new Text(currentPlayer.getName() + ", your turn!"));

        notificationBox.setFill(currentPlayer.getColor());

        if (currentPlayer.getColor() == Color.BLACK) {
            notificationText.setFill(Color.WHITE);
        }


    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.GAMEBOARD_IDLE);
    }
}
