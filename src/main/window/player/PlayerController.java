package window.player;

import java.util.ArrayList;

public class PlayerController {

    private ArrayList<Player> players;

    public PlayerController() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    // Getters and Setters
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
