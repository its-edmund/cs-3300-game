package window.player;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class PlayerController {

    private ArrayList<Player> players;
    private IntegerProperty currentPlayerIndex;
    private int currentMinigamePlayerIndex;

    public PlayerController() {
        players = new ArrayList<>();
        this.currentPlayerIndex = new SimpleIntegerProperty(0);
        this.currentMinigamePlayerIndex = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    // Getters and Setters

    public IntegerProperty getCurrentPlayerIndexProperty() {
        return currentPlayerIndex;
    }
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex.get());
    }
    public Player getCurrentMinigamePlayer() {
        return players.get(currentMinigamePlayerIndex);
    }
    public void endCurrentPlayerTurn() {
        if (currentPlayerIndex.get() == numPlayers() - 1) {
            currentPlayerIndex.set(0);
        } else {
            currentPlayerIndex.set(currentPlayerIndex.get() + 1);
        }
    }
    public boolean endCurrentMinigamePlayerTurn() {
        if (currentMinigamePlayerIndex == numPlayers() - 1) {
            currentMinigamePlayerIndex = 0;
            return true;
        } else {
            currentMinigamePlayerIndex++;
            return false;
        }
    }
    public int numPlayers() {
        return players.size();
    }
    public Player getPlayer(Player targetPlayer) {
        for (Player player : players) {
            if (player == targetPlayer)
                return player;
        }

        return null;
    }
    public Player get(int index) {
        if (index < 0 || index >= players.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return players.get(index);
        }
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
