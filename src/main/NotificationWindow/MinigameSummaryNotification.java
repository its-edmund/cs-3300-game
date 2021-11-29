package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import window.player.Player;

import javax.management.timer.TimerNotification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MinigameSummaryNotification extends AbstractClickNotification {

    private final double WIDTH = 250;
    private final double HEIGHT = 175;
    private final double MARGINS = 10;

    private final String TITLE = "MINIGAME RESULTS\n";

    public MinigameSummaryNotification(ViewHandler viewHandler) {
        super(viewHandler);

        notificationBox.setWidth(WIDTH);
        notificationBox.setHeight(HEIGHT);

        Label title = setupTitle();
        Label body = setupBodyText(viewHandler);

        setNotificationText(title, new Label() ,body);
    }

    private Label setupTitle() {
        Label label = new Label(TITLE);
        label.setFont(new Font(14));
        label.setTextAlignment(TextAlignment.CENTER);

        return label;
    }

    private Label setupBodyText(ViewHandler viewHandler) {

        ArrayList<Player> players =
                viewHandler.getState().getPlayerController().getPlayers();

        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return (int)(o2.getMinigameScore() - o1.getMinigameScore());
            }
        });

        Label label = new Label();
        label.setFont(new Font(10));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setMaxWidth(WIDTH - 2 * MARGINS);

        double maxScore = players.get(0).getMinigameScore();

        for (Player player : players) {
            String text = "";

            if (player.getMinigameScore() == maxScore) {
                player.setNumMinigamesWon(player.getNumMinigamesWon() + 1);
                player.setMoney(player.getMoney() + 500);
                text += player.getName() + " (WINNER!): " + Math.floor(player.getMinigameScore());
            } else {
                text += player.getName() + ": " + Math.floor(player.getMinigameScore());
            }

            label.setText(label.getText() + "\n" + text);
        }

        return label;
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.END_TURN);
//        viewHandler.getState().updateState(GameStates.MINIGAME_RESULTS);
    }
}
