package NotificationWindow;

import core.ViewHandler;
import javafx.scene.paint.Color;

import java.io.IOException;

public class VictoryNotification extends AbstractButtonNotification {

    public VictoryNotification(ViewHandler viewHandler) {
        super(viewHandler, 1);

        notificationText.setText("Victory!");

        notificationBox.setWidth(150);
        notificationBox.setHeight(100);
        notificationBox.setFill(Color.ORANGE);

        getButton(0).setText("Game Summary");
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
