package Minigame;

import core.GameStates;
import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class ExampleMinigame2 extends AbstractMinigame {

    Button button;

    // Keeps track of which player is playing the minigame

    public ExampleMinigame2(ViewHandler viewHandler) {
        super(viewHandler);

        minigameScreen.setWidth(400);
        minigameScreen.setHeight(250);

        // Shitty button "minigame"

//        button = new Button();
//        button.setText("Press Me!");
////        button.setTranslateY(-100);
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                handleButtonPress();
//            }
//        });
//
//        this.getChildren().add(button);

        new TheCircleMinigame(viewHandler, this);

        viewHandler.getState().updateState(GameStates.MINIGAME_INSTRUCTIONS);
    }

    @Override
    public String getMinigameTitle() {
        return "Example Minigame";
    }

    @Override
    public String getMinigameDescription() {
        return "    The goal of this minigame" +
                "\n" + "is to click the button.";
    }

    public void handleButtonPress() {
        viewHandler.getState().updateState(GameStates.MINIGAME_PLAY_OVER);
    }

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.END_TURN);
    }

}
