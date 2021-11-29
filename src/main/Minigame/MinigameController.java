package Minigame;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.control.Button;

import java.util.Random;


public class MinigameController extends AbstractMinigameController {

    Button button;
    AbstractMinigame minigame;

    // Keeps track of which player is playing the minigame

    public MinigameController(ViewHandler viewHandler) {
        super(viewHandler);

        minigameScreen.setWidth(400);
        minigameScreen.setHeight(250);

        // Randomly generate the next minigame
        Random rand = new Random(System.currentTimeMillis());
        int val = rand.nextInt(MinigameEnum.values().length);
        MinigameEnum chosenMinigame = MinigameEnum.values()[val];

//        MinigameEnum chosenMinigame = MinigameEnum.values()[1];

        viewHandler.getState().setCurrentMinigameType(chosenMinigame);
        chooseMinigame(chosenMinigame);

        viewHandler.getState().updateState(GameStates.MINIGAME_INSTRUCTIONS);
    }

    @Override
    public void launchMinigame() {
        minigame.playMinigame();
    }

    public void chooseMinigame(MinigameEnum game) {
        switch (game) {
            case SUGAR_HONEYCOMB:
                minigame = new SugarHoneycombMinigame(viewHandler, this);
                break;
            case THE_CIRCLE_MINIGAME:
                minigame = new TheCircleMinigame(viewHandler, this);
                break;
        }
    }

    @Override
    public String getMinigameTitle() {
        return minigame.getMinigameTitle();
    }

    @Override
    public String getMinigameDescription() {
        return minigame.getMinigameDescription();
    }

    @Override
    public void onExit() {
//        viewHandler.getState().updateState(GameStates.END_TURN);
    }

}
