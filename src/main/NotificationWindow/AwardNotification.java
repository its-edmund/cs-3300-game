package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.player.Award;
import window.player.Player;

public class AwardNotification extends AbstractButtonNotification {

    private final double WIDTH = 250;
    private final double HEIGHT = 150;

    public AwardNotification(ViewHandler viewHandler, Award award) {
        super(viewHandler, 1);

        setNotificationWidth(WIDTH);
        setNotificationHeight(HEIGHT);

        setPosX(0.5);
        setPosY(0.5);

        getButton(0).setText("OK");

        Text title = setupTitle(award);
        Text description = setupDescription(award);

        setNotificationText(title, description);
        setNotificationColor(Color.LIGHTGOLDENRODYELLOW);
    }

    private Text setupTitle(Award award) {

        Text title = new Text();

        String titleString = "AWARD: " + award.getAwardName();

        if (!award.isSoleWinner()) {
            titleString += (" (multiple award winners)");
        }

        title.setText(titleString);
        title.setFont(new Font(16));

        return title;
    }

    private Text setupDescription(Award award) {
        Text description = new Text();

        String descriptionString = award.getAwardDescription();
        description.setText(descriptionString);

        return description;
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.VICTORY_SCREEN);
    }
}
