package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameRulesNotification extends AbstractButtonNotification{

    private final double WIDTH = 400;
    private final double HEIGHT = 300;
    private final double MARGINS = 10;

    private final String BULLET = "\u2022";

    private final String TITLE = "Welcome to Masterpiece! These are the rules:\n";

    private final String TEXT_BODY =
            "\tPlayers take turns rolling the die. The goal of Masterpiece is to reach the end of the board first! " +
            "Each tile does something special!\n" +
            BULLET + "Green tiles gives you $200!\n" +
            BULLET + "Red tiles take away $100!\n" +
            BULLET + "Light blue tiles are chance tiles. Draw a chance card and see what happens!\n" +
            BULLET + "Slim grey rectangles on a tile is a paywall. To pass the paywall, ONE player must pay the fine." +
            " Once ONE player pays the fine, the paywall disappears!\n" +
            BULLET + "Dark blue tiles a minigame tile. When ONE player lands on a minigame tile, EVERYBODY " +
            "participates in a minigame! The winner(s) of the minigame get a cash prize!\n" +
            BULLET + "The Yellow tile is the goal tile. When you reach this tile, you win and the game is over!" ;

    private final String BUTTON_TEXT = "OK, lets go!";

    public GameRulesNotification(ViewHandler viewHandler) {
        super(viewHandler, 1);

        notificationBox.setWidth(WIDTH);
        notificationBox.setHeight(HEIGHT);

        getButton(0).setText(BUTTON_TEXT);

        Label label1 = setupTitle();
        Label label2 = setupBodyText();

        setNotificationText(label1, label2);
    }

    private Label setupTitle() {
        Label label = new Label(TITLE);
        label.setFont(new Font(14));
        label.setTextAlignment(TextAlignment.CENTER);

        return label;
    }

    private Label setupBodyText() {

        Label label = new Label(TEXT_BODY);
        label.setFont(new Font(10));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setMaxWidth(WIDTH - 2 * MARGINS);

        return label;
    }


    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.END_TURN);

        // DEBUG for victory screen
//        viewHandler.getState().updateState(GameStates.MOVING);
    }
}
