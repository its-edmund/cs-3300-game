package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class VictoryNotification extends AbstractButtonNotification {

    public VictoryNotification(ViewHandler viewHandler) {
        super(viewHandler, 1);

        setNotificationText(new Text("Victory!"));

        notificationBox.setWidth(150);
        notificationBox.setHeight(100);
        notificationBox.setFill(Color.ORANGE);

        getButton(0).setText("Game Summary");
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.VICTORY_SCREEN);
    }
}
