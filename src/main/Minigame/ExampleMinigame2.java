package Minigame;

import core.GameStates;
import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Random;


public class ExampleMinigame2 extends AbstractMinigame {

    Button button;

    // Keeps track of which player is playing the minigame

    public ExampleMinigame2(ViewHandler viewHandler) {
        super(viewHandler);

        minigameScreen.setWidth(400);
        minigameScreen.setHeight(250);

        // Randomly generate the next minigame
        Random rand = new Random();
        int val = rand.nextInt(MinigameEnum.values().length);
        viewHandler.getState().setCurrentMinigameType(MinigameEnum.values()[val]);

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

        viewHandler.getState().updateState(GameStates.MINIGAME_INSTRUCTIONS);
    }

    @Override
    public void launchMinigame(MinigameEnum game) {

        switch (game) {
            case SUGAR_HONEYCOMB:
                new SugarHoneycombMinigame(viewHandler, this);
                break;
            case THE_CIRCLE_MINIGAME:
                new TheCircleMinigame(viewHandler, this);
                break;
        }
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
