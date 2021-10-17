package window.gameboard;

import core.ViewHandler;
import window.player.Player;
import window.player.PlayerController;

public class GameStateController {

    protected ViewHandler viewHandler;
    private GameboardController gameboardController;

    private int playerTurnIndex;

    public GameStateController(ViewHandler viewHandler, GameboardController gameboardController) {
        this.viewHandler = viewHandler;
        this.gameboardController = gameboardController;
        playerTurnIndex = 0;
    }

    public void handleDiceRoll(int movement) {
        Player movingPlayer = viewHandler.getState().getPlayerController().get(playerTurnIndex);
        boolean gotChance = movingPlayer.getPlayerMover().movePlayer(movingPlayer, movement);

        if (gotChance) {

        }


        if (playerTurnIndex == viewHandler.getState().getPlayerController().getPlayers().size() - 1) {
            playerTurnIndex = 0;
        } else {
            playerTurnIndex++;
        }

        gameboardController.changePlayerStatus(playerTurnIndex);
    }

}
