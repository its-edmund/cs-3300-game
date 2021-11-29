package window.player;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Comparator;

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

    // Determine Awards
    public void giveAward(AwardEnum awardType) {
        ArrayList<Player> winners = new ArrayList<>();

        switch (awardType){
            case MINIGAME_MASTER:
            case LUCKIEST:
            case RICHEST:
                giveMostOfAward(awardType);
        }

    }
    private void giveMostOfAward(AwardEnum awardType) {

        int max = -1;
        boolean multipleMax = false;

        // find the max
        for (Player player : players) {
            if (getMostOfCriterion(player, awardType) > max) {
                max = getMostOfCriterion(player, awardType);
                multipleMax = false;
            } else if (getMostOfCriterion(player, awardType) == max) {
                max = getMostOfCriterion(player, awardType);
                multipleMax = true;
            }

        }

        // add all players that have max number
        for (Player player : players) {
            if (getMostOfCriterion(player, awardType) == max) {
                player.addAward(new Award(awardType, !multipleMax));
            }
        }
    }
    private int getMostOfCriterion(Player player, AwardEnum awardType) {
        switch (awardType) {
            case RICHEST:
                return player.getMoney();
            case LUCKIEST:
                return player.getNumChanceTilesLandedOn();
            case MINIGAME_MASTER:
                return player.getNumMinigamesWon();
            default:
                return -1;
        }
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
