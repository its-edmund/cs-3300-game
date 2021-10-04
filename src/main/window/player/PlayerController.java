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

    public Player getPlayer(Player targetPlayer) {
        for (Player player : players) {
            if (player == targetPlayer)
                return player;
        }

        return null;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
