package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import window.player.Award;
import window.player.Player;

public class AwardNotification extends AbstractButtonNotification {

    private final double WIDTH = 200;
    private final double HEIGHT = 150;
    private final double MARGINS = 10;

    public AwardNotification(ViewHandler viewHandler, Award award) {
        super(viewHandler, 1);

        setNotificationWidth(WIDTH);
        setNotificationHeight(HEIGHT);

        setPosX(0.5);
        setPosY(0.5);

        getButton(0).setText("OK");

        Label title = setupTitle(award);
        Label linebreak = setupLinebreak();
        Label description = setupDescription(award);

        setNotificationText(title, linebreak, description);
        setNotificationColor(Color.LIGHTGREEN);
    }

    private Label setupTitle(Award award) {

        Label title = new Label();

        String titleString = "AWARD: " + award.getAwardName();

        if (!award.isSoleWinner()) {
            titleString += (" (multiple award winners)");
        }

        title.setText(titleString);
        title.setFont(new Font(12));
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setMaxWidth(WIDTH - 2 * MARGINS);

        return title;
    }

    private Label setupDescription(Award award) {
        Label description = new Label();

        String descriptionString = award.getAwardDescription();
        description.setText(descriptionString);
        description.setFont(new Font(10));
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.CENTER);
        description.setMaxWidth(WIDTH - 2 * MARGINS);

        return description;
    }

    private Label setupLinebreak() {
        return new Label();
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.VICTORY_SCREEN);
    }
}
