package NotificationWindow;

import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tile.Wall;
import window.gameboard.GameStateController;
import window.player.Player;

public class WallNotification extends StackPane {

    private GameStateController gameStateController;

    private Rectangle promptBox;
    private Button yesButton;
    private Button noButton;

    private Text barrierDescription;
    private Text errorMessage;

    private final double WIDTH = 250;
    private final double HEIGHT = 100;

    private ButtonDecision decision;

    public WallNotification(GameStateController gameStateController) {

        super();

        this.gameStateController = gameStateController;

        decision = ButtonDecision.UNDECIDED;

        promptBox = new Rectangle();

        promptBox.setWidth(WIDTH);
        promptBox.setHeight(HEIGHT);
        promptBox.setStrokeWidth(2);
        promptBox.setStroke(Color.BLACK);
        promptBox.setFill(Color.LIGHTYELLOW);

        yesButton = new Button();
        yesButton.setText("Yes");
        yesButton.setTranslateX(-50);
        yesButton.setTranslateY(20);

        noButton = new Button();
        noButton.setText("No");
        noButton.setTranslateX(50);
        noButton.setTranslateY(20);

        yesButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                handleYesButton();
            }
        });
        noButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                handleNoButton();
            }
        });

        barrierDescription = new Text();
        barrierDescription.setText("Pay $500 to remove the barrier?");
        barrierDescription.setTranslateX(0);
        barrierDescription.setTranslateY(-10);

        errorMessage = new Text();
        errorMessage.setText("ERROR: Insufficient funds!");
        errorMessage.setFill(Color.RED);
        errorMessage.setTranslateX(0);
        errorMessage.setTranslateY(-5);
        errorMessage.setVisible(false);

        // Makes the notification visible
        setActive(true);

        this.getChildren().addAll(promptBox, yesButton, noButton,
                barrierDescription, errorMessage);
    }

    // Button Handlers
    private void handleYesButton() {

        Player currentPlayer = gameStateController.getMovingPlayer();

        if (currentPlayer.getMoney() < 500) {
            displayErrorMessage();
        } else {

            currentPlayer.setMoney(currentPlayer.getMoney() - 500);

            Wall blockingWall = currentPlayer.getBlockingTile().getWall();
            blockingWall.setActive(false);

            gameStateController.resumePlayerMoveAfterWallRemoved();
            setActive(false);
        }

    }
    private void handleNoButton() {

        Player currentPlayer = gameStateController.getMovingPlayer();
        currentPlayer.getPlayerMover().movePlayer(-3);

        gameStateController.endPlayerMove();
        setActive(false);
    }

    // Show error message
    private void displayErrorMessage() {
        errorMessage.setVisible(true);
    }

    // Button Decision
    public ButtonDecision getDecision() {
        return decision;
    }

    public void setActive(boolean active) {
        this.setVisible(active);
    }
    public boolean getActive() {
        return this.isVisible();
    }
}
