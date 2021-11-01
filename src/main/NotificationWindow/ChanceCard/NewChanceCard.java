package NotificationWindow.ChanceCard;

import core.AppViewHandler;
import core.GameStates;
import core.ResizableStackPane;
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

public class NewChanceCard extends ResizableStackPane {

    GameStateController gameStateController;

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

    public NewChanceCard(GameStateController gameStateController) {

        this.gameStateController = gameStateController;

        seed = (new Random()).nextInt(NUM_CHANCE_CARDS);

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

        Player player = gameStateController.getMovingPlayer();

        if (seed == 0) {
            // add money
            player.setMoney(player.getMoney() + 300);

            // make an active interface
            this.setVisible(false);

            // end the current player's turn
            gameStateController.endPlayerMove();
        } else if ( seed == 1) {
            // subtract money
            player.setMoney(player.getMoney() - 100);

            // make an active interface
            this.setVisible(false);

            // end the current player's turn
            gameStateController.endPlayerMove();
        } else if (seed == 2) {

            // make an active interface
            this.setVisible(false);

            // advance 3 squares
            gameStateController.setCurrentGamestate(GameStates.MOVING);
            player.getPlayerMover().movePlayer(3);
        } else if (seed == 3) {

            // make an active interface
            this.setVisible(false);

            //advance 1 squares
            gameStateController.setCurrentGamestate(GameStates.MOVING);
            player.getPlayerMover().movePlayer(1);
        } else if (seed == 4) {

            // make an active interface
            this.setVisible(false);

            // go back 2 squares
            gameStateController.setCurrentGamestate(GameStates.MOVING);
            player.getPlayerMover().movePlayer(-2);
        } else if (seed == 5) {

            // make an active interface
            this.setVisible(false);

            // go back 1 square
            gameStateController.setCurrentGamestate(GameStates.MOVING);
            player.getPlayerMover().movePlayer(-1);
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
