package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameRulesNotification extends AbstractButtonNotification{

    public GameRulesNotification(ViewHandler viewHandler) {
        super(viewHandler, 1);

        notificationBox.setWidth(400);
        notificationBox.setHeight(250);

        getButton(0).setText("OK, lets go!");

        Text line1 = new Text("Welcome to Masterpiece! These are the rules:");
        line1.setFont(new Font(14));

        Text line2 = new Text("Players take turns rolling the die. The goal of Masterpiece \n" +
                "is to reach the end of the board first!");
        line2.setFont(new Font(8));
        Text line3 = new Text("Each tile does something special!");
        line3.setFont(new Font(8));
        Text line4 = new Text("This tile gives you $100!");
        line4.setFont(new Font(8));
        Text line5 = new Text("This tile takes away $100!");
        line5.setFont(new Font(8));
        Text line6 = new Text("This is a chance tile. Draw a chance card and see what happens!");
        line6.setFont(new Font(8));
        Text line7 = new Text("This is a paywall. To pass the paywall, ONE player must pay the fine. \n" +
                "Once ONE player pays the fine, the paywall disappears!");
        line7.setFont(new Font(8));
        Text line8 = new Text("This is a minigame tile. When ONE player lands on a minigame tile, EVERYBODY \n" +
                "participates in a minigame!");
        line8.setFont(new Font(8));
        Text line9 = new Text("This is the goal tile. When you reach this tile, you win and the game is over!");
        line9.setFont(new Font(8));

        setNotificationText(line1, line2, line3, line4, line5, line6, line7, line8, line9);
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.END_TURN);
    }
}
