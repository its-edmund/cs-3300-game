package state;

import core.GameStates;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import window.player.PlayerController;

public class State {

    private PlayerController playerController;
    private GameStates currentState = GameStates.GAMEBOARD_IDLE;
    private GameStates prevState = GameStates.GAMEBOARD_IDLE;
    private Pane board;

    private Node currentNotification;

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    // State Operations
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

    // Board Operations
    public void setGameBoard(Pane board) {
        this.board = board;
    }
    public void addNodeToBoard(Node node) {
        if (this.board == null) {
            throw new NullPointerException("Board is uninitialized!");
        } else {
            board.getChildren().add(node);
        }
    }
    public void removeNodeFromBoard(Node node) {
        if (this.board == null) {
            throw new NullPointerException("Board is uninitialized!");
        } else {
            board.getChildren().remove(node);
        }
    }
    public void addNotification(Node node) {
        currentNotification = node;
        addNodeToBoard(currentNotification);
    }
    public void removeNotification() {
        removeNodeFromBoard(currentNotification);
    }

}
