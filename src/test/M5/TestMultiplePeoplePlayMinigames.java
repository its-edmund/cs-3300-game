package M5;

import Minigame.MinigameEnum;
import core.AppViewHandler;
import core.GameStates;
import core.ViewHandler;
import org.junit.Test;
import window.gameboard.GameStateController;
import window.player.Player;
import window.player.PlayerController;

import static junit.framework.TestCase.assertEquals;

public class TestMultiplePeoplePlayMinigames {

    @Test
    public void testMultiplePeoplePlayCircleGame() {
        ViewHandler viewHandler = new AppViewHandler(null, null);
        viewHandler.getState().updateState(GameStates.MINIGAME_LAUNCH);
        viewHandler.getState().setCurrentMinigameType(MinigameEnum.THE_CIRCLE_MINIGAME);

        PlayerController playerController = new PlayerController();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        playerController.addPlayer(player1);
        playerController.addPlayer(player2);
        playerController.addPlayer(player3);
        playerController.addPlayer(player4);

        viewHandler.getState().setPlayerController(playerController);

        viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);

        assertEquals(false, viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn());
        assertEquals(false, viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn());
        assertEquals(false, viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn());
        assertEquals(true, viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn());
    }

    @Test
    public void testMultiplePeoplePlayHoneycombGame() {

        ViewHandler viewHandler = new AppViewHandler(null, null);
        viewHandler.getState().updateState(GameStates.MINIGAME_LAUNCH);
        viewHandler.getState().setCurrentMinigameType(MinigameEnum.SUGAR_HONEYCOMB);

        PlayerController playerController = new PlayerController();
        Player player1 = new Player();
        Player player2 = new Player();

        playerController.addPlayer(player1);
        playerController.addPlayer(player2);

        viewHandler.getState().setPlayerController(playerController);

        viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);

        assertEquals(false, viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn());
        assertEquals(true, viewHandler.getState().getPlayerController().endCurrentMinigamePlayerTurn());
    }

}
