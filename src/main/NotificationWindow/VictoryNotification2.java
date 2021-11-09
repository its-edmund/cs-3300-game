package NotificationWindow;

import core.ViewHandler;
import javafx.scene.paint.Color;

import java.io.IOException;

public class VictoryNotification2 extends AbstractButtonNotification {

    public VictoryNotification2(ViewHandler viewHandler) {
        super(viewHandler, 1);

        notificationText.setText("Victory!");
        notificationText.setTranslateY(-10);

        notificationBox.setWidth(150);
        notificationBox.setHeight(75);
        notificationBox.setFill(Color.ORANGE);

        buttons.get(0).setText("Game Summary");
    }

    @Override
    public void onExit() {
        try {
            viewHandler.launchVictoryScreen();
        } catch (IOException e) {
            System.out.println("Error launching victory screen.");
            e.printStackTrace();
        }
    }
}
