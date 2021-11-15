package NotificationWindow;

import core.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractButtonNotification extends AbstractNotification {

    protected HBox buttons;
    private int indexOfButtonClicked;

    public AbstractButtonNotification(ViewHandler viewHandler, int numButtons) {
        super(viewHandler);

        buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        for (int i = 0; i < numButtons; i++) {
            Button button = new Button();
            button.setText("Option " + i);
            button.setTranslateX( 50 * (i - (numButtons - 1) / 2.0));
            button.setTranslateY(20);

            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    recordButtonClicked(finalI);
                    onExit();
                }
            });

            buttons.getChildren().add(button);
        }

        layoutBox.getChildren().add(buttons);
    }

    @Override
    public void setNotificationText(Node... nodes) {
        // Call this to add text to the notification
        super.setNotificationText(nodes);

        // Add and remove the HBox of buttons
        // This is so the buttons are always positioned at the bottom of the notification
        layoutBox.getChildren().remove(buttons);
        layoutBox.getChildren().add(buttons);
    }


    private void recordButtonClicked(int i) {
        indexOfButtonClicked = i;
    }
    protected int getIndexOfButtonClicked() {
        return indexOfButtonClicked;
    }
    public Button getButton(int i) {
        return (Button)buttons.getChildren().get(i);
    }

}
