package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class GenericButtonNotification extends AbstractButtonNotification {

    private GameStates exitState = null;

    public GenericButtonNotification(ViewHandler viewHandler, GameStates exitState) {
        super(viewHandler, 1);

        this.exitState = exitState;

        getButton(0).setOnAction(event -> {
            onExit();
        });

    }


    @Override
    public void onExit() {
        if (exitState == null) {
            System.out.println("exit state still not initialized!");
        } else {
            viewHandler.getState().updateState(exitState);
        }

    }
}
