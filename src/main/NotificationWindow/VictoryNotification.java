package NotificationWindow;

import core.AppViewHandler;
import core.ResizableStackPane;
import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.gameboard.GameStateController;

import java.io.IOException;

public class VictoryNotification extends AbstractNotification {

    ViewHandler viewHandler;

    Rectangle promptBox;
    Text promptText;
    Button button;

    private final double WIDTH = 150;
    private final double HEIGHT = 100;

    public VictoryNotification(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;

        promptBox = new Rectangle();
        promptBox.setWidth(WIDTH);
        promptBox.setHeight(HEIGHT);
        promptBox.setFill(Color.CORNFLOWERBLUE);
        promptBox.setStroke(Color.BLACK);

        promptText = new Text();
        promptText.setFont(new Font(10));
        promptText.setText("Victory!");
        promptText.setTranslateY(-20);

        button = new Button();
        button.setText("Game Summary");
        button.setTranslateY(20);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                onExit();
            }
        });

        this.getChildren().addAll(promptBox, promptText, button);
    }

    public void onExit() {
        try {
            viewHandler.launchVictoryScreen();
        } catch (IOException e) {
            System.out.println("Error launching victory screen.");
            e.printStackTrace();
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
