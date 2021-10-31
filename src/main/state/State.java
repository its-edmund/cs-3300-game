package state;

import core.GameStates;
import window.player.PlayerController;

public class State {

    private PlayerController playerController;
    private GameStates currentState;

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }
    public GameStates getCurrentState() {
        return currentState;
    }
    public void setCurrentState(GameStates newState) {
        currentState = newState;
    }
}
