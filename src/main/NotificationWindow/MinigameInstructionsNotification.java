package NotificationWindow;

import Minigame.AbstractMinigameController;
import Minigame.MinigameController;
import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class MinigameInstructionsNotification extends AbstractButtonNotification {

    private final double WIDTH = 250;
    private final double HEIGHT = 175;
    private final double MARGINS = 10;

    private final String BUTTON_TEXT = "OK, Got it!";

    public MinigameInstructionsNotification(ViewHandler viewHandler) {
        super(viewHandler, 1);

        notificationBox.setWidth(WIDTH);
        notificationBox.setHeight(HEIGHT);

        getButton(0).setText(BUTTON_TEXT);

        this.setPosX(0.5);
        this.setPosY(0.5);

        AbstractMinigameController minigameController =
                viewHandler.getState().getCurrentMinigame();

        Label label1 = setupTitle(minigameController);
        Label label2 = setupFillerText();
        Label label3 = setupBodyText(minigameController);

        setNotificationText(label1, label2, label3);
    }

    private Label setupTitle(AbstractMinigameController minigameController) {

        Label label = new Label();
        label.setText(minigameController.getMinigameTitle());
        label.setFont(new Font(12));
        label.setTextAlignment(TextAlignment.CENTER);

        return label;
    }

    private Label setupBodyText(AbstractMinigameController minigameController) {

        Label label = new Label();
        label.setText("\t" + minigameController.getMinigameDescription());
        label.setFont(new Font(10));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setMaxWidth(WIDTH - 2 * MARGINS);

        return label;
    }

    private Label setupFillerText() {
        Label label = new Label();
        label.setText("");

        return label;
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.MINIGAME_PLAYER_READY);
    }
}
