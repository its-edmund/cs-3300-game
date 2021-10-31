package M4;

import core.AppViewHandler;
import core.ViewHandler;
import org.junit.Test;
import window.player.Player;
import window.victory_screen.VictoryScreenController;
import static junit.framework.TestCase.assertEquals;

public class WinScreenTests {

    @Test
    public void testHasWinner() {
        ViewHandler vh = null;
        VictoryScreenController vsController = new VictoryScreenController(vh);
        vsController.initWinnerList(true);
        assertEquals(vsController.winnerList.get(3).getToken().getTokenLocation(), 1);
    }

    @Test
    public void testPlayersAreTied() {
        ViewHandler vh = null;
        VictoryScreenController vsController = new VictoryScreenController(vh);
        vsController.initWinnerList(false);
        boolean playersAreTied = true;
        for (Player p : vsController.winnerList) {
            if (p.getToken().getTokenLocation() != 0) { playersAreTied = false ; }
        }
        assertEquals(playersAreTied, true);
    }
}

