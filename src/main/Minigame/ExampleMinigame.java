package Minigame;

import core.GameStates;
import core.ResizableStackPane;
import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.player.Player;


public class ExampleMinigame extends ResizableStackPane {

    ViewHandler viewHandler;

    private final int WIDTH = 200;
    private final int HEIGHT = 100;

    Rectangle miniGameScreen;
    Text text;
    Button button;

    public ExampleMinigame(ViewHandler viewHandler) {
        super();

        this.viewHandler = viewHandler;

        this.setPosX(0.5);
        this.setPosY(0.5);

        miniGameScreen = new Rectangle();
        miniGameScreen.setWidth(WIDTH);
        miniGameScreen.setHeight(HEIGHT);
        miniGameScreen.setFill(Color.WHITE);
        miniGameScreen.setStroke(Color.BLACK);

        String message = "click the button to win!";
        Player currentPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();
        message = currentPlayer.getName() + ": " + message;

        text = new Text();
        text.setFont(new Font(10));
        text.setText(message);

        button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onExit();
            }
        });

        this.getChildren().addAll(miniGameScreen, text, button);
    }

    public void onExit() {
        viewHandler.getState().updateState(GameStates.END_TURN);
    }

}
