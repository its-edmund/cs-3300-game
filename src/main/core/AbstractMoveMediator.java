package core;

import window.gameboard.GameboardController;
import window.player.Player;

public abstract class AbstractMoveMediator {

    protected GameboardController gameboardController;

    public AbstractMoveMediator(GameboardController gameboardController) {
        this.gameboardController = gameboardController;
    }

    public abstract boolean movePlayer(Player player, int moveAmount);
}
