package NotificationWindow;

import core.AppPaths;
import core.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractButtonNotification extends AbstractNotification {

    protected HBox buttons;
    private int indexOfButtonClicked;
    private Media buttonClickSound;

    public AbstractButtonNotification(ViewHandler viewHandler, int numButtons) {
        super(viewHandler);

        buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        setupButtonClick();

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
                    playButtonClicked();
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

    private void setupButtonClick() {
        buttonClickSound = new Media( new File(AppPaths.SOUND_PATH + "button_click.wav").toURI().toString());
    }

    public void playButtonClicked() {
        MediaPlayer mediaPlayer = new MediaPlayer(buttonClickSound);
        mediaPlayer.play();
    }
}
