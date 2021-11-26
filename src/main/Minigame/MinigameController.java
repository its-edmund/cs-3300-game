package Minigame;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Button;

import java.util.Random;


public class MinigameController extends AbstractMinigameController {

    Button button;

    // Keeps track of which player is playing the minigame

    public MinigameController(ViewHandler viewHandler) {
        super(viewHandler);

        minigameScreen.setWidth(400);
        minigameScreen.setHeight(250);

        // Randomly generate the next minigame
//        Random rand = new Random();
//        int val = rand.nextInt(MinigameEnum.values().length);
//        viewHandler.getState().setCurrentMinigameType(MinigameEnum.values()[val]);

        viewHandler.getState().setCurrentMinigameType(MinigameEnum.values()[1]);

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

    @Override
    public void onExit() {
        viewHandler.getState().updateState(GameStates.END_TURN);
    }

}
