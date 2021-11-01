package core;

import window.gameboard.GameboardController;
import window.player.Player;

public abstract class AbstractMoveMediator {

    protected GameboardController gameboardController;
    protected Player player;

    public AbstractMoveMediator(Player player, GameboardController gameboardController) {
        this.gameboardController = gameboardController;
        this.player = player;
    }

    public abstract PostMoveActionType movePlayer(int moveAmount);
}
