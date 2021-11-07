package NotificationWindow;

import core.AppViewHandler;
import core.GameStates;
import core.ResizableStackPane;
import core.ViewHandler;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.gameboard.GameStateController;
import window.gameboard.GameboardController;
import window.player.Player;

import java.util.Random;

public class ChanceCardNotification extends AbstractNotification {

    GameStateController gameStateController;
    ViewHandler viewHandler;

    Rectangle promptBox;
    Text promptText;

    private final double WIDTH = 100;
    private final double HEIGHT = 50;

    private final int NUM_CHANCE_CARDS = 6;
    private int seed;

    private String[] chanceCardTextDescriptions = {
            "Payday! You win $300!",
            "Oh no! You lose $100!",
            "Advance 3 squares",
            "Advance 1 squares",
            "Go back 2 squares",
            "Go back 1 squares"
    };

    public ChanceCardNotification(GameStateController gameStateController, ViewHandler viewHandler) {

        this.gameStateController = gameStateController;
        this.viewHandler = viewHandler;

//        seed = (new Random()).nextInt(NUM_CHANCE_CARDS);
        seed = 2;

        promptBox = new Rectangle();
        promptBox.setWidth(WIDTH);
        promptBox.setHeight(HEIGHT);
        promptBox.setFill(Color.CORNFLOWERBLUE);
        promptBox.setStroke(Color.BLACK);

        promptText = new Text();
        promptText.setFont(new Font(10));
        promptText.setText(chanceCardTextDescriptions[seed]);

        this.getChildren().addAll(promptBox, promptText);

        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Chance card clicked!");
                onExit();
            }
        };
        //Adding event Filter
        promptBox.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void onExit() {

        Player player = viewHandler.getState().getPlayerController().getCurrentPlayer();
        this.setVisible(false);

        if (seed == 0) {
            // add money
            player.setMoney(player.getMoney() + 300);
            viewHandler.getState().updateState(GameStates.END_TURN);

        } else if ( seed == 1) {
            // subtract money
            player.setMoney(player.getMoney() - 100);
            viewHandler.getState().updateState(GameStates.END_TURN);


        } else if (seed == 2) {

            // advance 3 squares
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(4);
            viewHandler.getState().updateState(GameStates.MOVING);


        } else if (seed == 3) {

            //advance 1 squares
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(1);
            viewHandler.getState().updateState(GameStates.MOVING);

        } else if (seed == 4) {

            // go back 2 squares
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(-2);
            viewHandler.getState().updateState(GameStates.MOVING);

        } else if (seed == 5) {

            // go back 1 square
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(-1);
            viewHandler.getState().updateState(GameStates.MOVING);

        }
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
