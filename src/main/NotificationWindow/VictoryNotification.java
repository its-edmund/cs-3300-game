package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class VictoryNotification extends AbstractButtonNotification {

    private final double WIDTH = 150;
    private final double HEIGHT = 100;
    private final double MARGINS = 10;



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
