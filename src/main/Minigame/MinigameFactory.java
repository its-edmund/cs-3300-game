package Minigame;

import core.GameStates;
import core.ViewHandler;

public class MinigameFactory {

    ViewHandler viewHandler;

    public MinigameFactory(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public AbstractMinigameController createMinigame(GameStates minigameStates) {

        AbstractMinigameController minigame = null;

        if (minigameStates == GameStates.MINIGAME_LAUNCH) {
            minigame = new MinigameController(viewHandler);
            minigame.setPosX(0.5);
            minigame.setPosY(0.5);
        }

        return minigame;
    }

    public void onExit() {
        viewHandler.getState().updateState(GameStates.NEW_TURN);
    }

}
