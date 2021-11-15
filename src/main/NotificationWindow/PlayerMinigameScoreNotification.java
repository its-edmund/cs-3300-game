package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import window.player.Player;

public class PlayerMinigameScoreNotification extends AbstractTimedNotification {

    public PlayerMinigameScoreNotification(ViewHandler viewHandler) {
        super(viewHandler, 3);

        setNotificationWidth(250);
        setNotificationHeight(150);

        Player currentMinigamePlayer =
                viewHandler.getState().getPlayerController().getCurrentMinigamePlayer();

        Label title = new Label("Good job, " + currentMinigamePlayer.getName() + "!\n");
        Label score = new Label("Score: "
                + currentMinigamePlayer.getMinigameScore() + " points\n");

        setNotificationText(title, score);
    }

    @Override
    public void onExit() {
        boolean allPlayersPlayed =
                viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn();

        if (allPlayersPlayed) {
            // If all players have played, end the minigame
            viewHandler.getState().updateState(GameStates.END_TURN);
        } else {
            // Otherwise, prepare the next player for the minigame
            viewHandler.getState().updateState(GameStates.MINIGAME_PLAYER_READY);
        }
    }
}
