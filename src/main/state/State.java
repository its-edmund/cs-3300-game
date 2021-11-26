package state;

import Minigame.AbstractMinigameController;
import Minigame.MinigameEnum;
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
    private AbstractMinigameController currentMinigame;
    private MinigameEnum currentMinigameType;

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

        // First, remove the current notification
        removeNotification();

        currentNotification = node;
        addNodeToBoard(currentNotification);
    }
    public void removeNotification() {
        if (currentNotification != null)
            removeNodeFromBoard(currentNotification);
    }

    // Minigame Operations
    public AbstractMinigameController getCurrentMinigame() {
        return currentMinigame;
    }
    public void setCurrentMinigameType(MinigameEnum type) {
        currentMinigameType = type;
    }
    public MinigameEnum getCurrentMinigameType() {
        return currentMinigameType;
    }
    public void addMinigame(AbstractMinigameController node) {
        currentMinigame = node;
        addNodeToBoard(currentMinigame);
    }
    public void removeMinigame() {
        removeNodeFromBoard(currentMinigame);
    }
}
