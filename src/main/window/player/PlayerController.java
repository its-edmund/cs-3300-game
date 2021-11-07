package window.player;

import java.util.ArrayList;

public class PlayerController {

    private ArrayList<Player> players;
    private int currentPlayerIndex;

    public PlayerController() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    // Getters and Setters

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public void endCurrentPlayerTurn() {
        if (currentPlayerIndex == numPlayers() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
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
