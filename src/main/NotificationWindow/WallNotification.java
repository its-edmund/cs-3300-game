package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tile.Wall;
import window.player.Player;

public class WallNotification extends AbstractButtonNotification {

    private Text errorMessage;

    public WallNotification(ViewHandler viewHandler) {
        super(viewHandler, 2);

        getButton(0).setText("Yes");
        getButton(1).setText("No");

        notificationBox.setWidth(250);
        notificationBox.setHeight(100);

        setNotificationText(new Text("Pay $500 to remove the barrier?"));
//        notificationText.setText("Pay $500 to remove the barrier?");

        errorMessage = new Text();
        errorMessage.setText("ERROR: Insufficient funds!");
        errorMessage.setFill(Color.RED);
        errorMessage.setVisible(false);

        layoutBox.getChildren().add(errorMessage);
    }

    @Override
    public void onExit() {
        Player currentPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();

        if (getIndexOfButtonClicked() == 0) {
            // Handle Yes Button

            if (currentPlayer.getMoney() < 500) {
                displayErrorMessage();
            } else {

                currentPlayer.setMoney(currentPlayer.getMoney() - 500);

                Wall blockingWall = currentPlayer.getBlockingTile().getWall();
                blockingWall.setActive(false);

                viewHandler.getState().updateState(GameStates.MOVING);
            }
        } else {
            // Handle No Button
            currentPlayer.getPlayerMover().setRemainingMoves(-3);

            viewHandler.getState().updateState(GameStates.MOVING);
        }
    }

    private void displayErrorMessage() {
        notificationText.setTranslateY(-25);
        errorMessage.setTranslateY(-10);
        errorMessage.setVisible(true);
    }
}
