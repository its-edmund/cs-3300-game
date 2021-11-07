package NotificationWindow;

import core.AppViewHandler;
import core.GameStates;
import core.ResizableStackPane;
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

public class WallNotification extends AbstractNotification {

    private GameStateController gameStateController;
    private ViewHandler viewHandler;

    private Rectangle promptBox;
    private Button yesButton;
    private Button noButton;

    private Text barrierDescription;
    private Text errorMessage;

    private final double WIDTH = 250;
    private final double HEIGHT = 100;

    public WallNotification(GameStateController gameStateController, ViewHandler viewHandler) {

        super();

        this.gameStateController = gameStateController;
        this.viewHandler = viewHandler;

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

//        AppViewHandler.primaryStage.titleProperty().bind(this.widthProperty().asString());
//        AppViewHandler.primaryStage.titleProperty().bind(this.heightProperty().asString());

        this.getChildren().addAll(promptBox, yesButton, noButton,
                barrierDescription, errorMessage);
    }

    // Button Handlers
    private void handleYesButton() {

        Player currentPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();

        if (currentPlayer.getMoney() < 500) {
            displayErrorMessage();
        } else {

            currentPlayer.setMoney(currentPlayer.getMoney() - 500);

            Wall blockingWall = currentPlayer.getBlockingTile().getWall();
            blockingWall.setActive(false);

            viewHandler.getState().updateState(GameStates.MOVING);
        }

    }
    private void handleNoButton() {

        Player currentPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();
        currentPlayer.getPlayerMover().setRemainingMoves(-3);

        viewHandler.getState().updateState(GameStates.MOVING);
    }

    // Show error message
    private void displayErrorMessage() {
        errorMessage.setVisible(true);
    }

    public double getPosX() {

        double screenWidth = (AppViewHandler.getScreenWidth() - this.getWidth());
        double screenHeight = (AppViewHandler.getScreenHeight() - this.getHeight()
                - 40 - 85);
        double newX = posX * screenHeight + (screenWidth - screenHeight) / 2;

        return newX;
    }
    public double getPosY() {
        double screenHeight = (AppViewHandler.getScreenHeight() - this.getHeight()
                - 40 - 85);
        double newY = posY * screenHeight;

        return newY;
    }
}
