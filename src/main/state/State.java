package state;

import core.GameStates;
import window.player.PlayerController;

public class State {

    private PlayerController playerController;
    private GameStates currentState;
    private GameStates prevState;

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public GameStates getCurrentState() {
        return currentState;
    }
    public GameStates getPrevState() {return  prevState; }

    public void updateState(GameStates newState) {
        prevState = currentState;
        currentState = newState;
    }

    public void updateState() {
        prevState = currentState;
    }
    public boolean stateChangeOccurred() {
        return (currentState != prevState);
    }

    public boolean didStateTransitionOccur(GameStates oldState, GameStates newState) {
        return (newState == currentState) && (oldState == prevState);
    }
    public boolean transitionedFromState(GameStates oldState) {
        return (prevState == oldState);
    }
    public boolean transitionedToState(GameStates newState) {
        return (currentState == newState);
    }


}
