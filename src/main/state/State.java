package state;

import window.player.PlayerController;

public class State {

    private PlayerController playerController;

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

}
