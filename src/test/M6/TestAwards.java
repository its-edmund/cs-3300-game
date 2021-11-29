package M6;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import window.player.Award;
import window.player.AwardEnum;
import window.player.Player;
import window.player.PlayerController;

import static junit.framework.TestCase.*;

public class TestAwards {

    PlayerController playerController;
    Player p1;
    Player p2;
    Player p3;
    Player p4;

    @Before
    public void setupPlayers() {
        playerController = new PlayerController();

        p1 = new Player("A", null, 1000, null);
        p2 = new Player("B", null, 1000, null);
        p3 = new Player("C", null, 1000, null);
        p4 = new Player("D", null, 1000, null);

        playerController.addPlayer(p1);
        playerController.addPlayer(p2);
        playerController.addPlayer(p3);
        playerController.addPlayer(p4);
    }

    @Test
    public void testRichestAward() {
        p1.setMoney(1050);

        playerController.giveAward(AwardEnum.RICHEST);

        assertEquals(p1.getAwards().size(), 1);
        assertEquals(p1.getAwards().get(0).isSoleWinner(), true);
    }

    @Test
    public void testMinigameMasterAward() {
        p2.setNumMinigamesWon(5);

        playerController.giveAward(AwardEnum.MINIGAME_MASTER);

        assertEquals(p2.getAwards().size(), 1);
        assertEquals(p2.getAwards().get(0).isSoleWinner(), true);
    }

    @Test
    public void testLuckiestAward() {
        p2.setNumChanceTilesLandedOn(3);

        playerController.giveAward(AwardEnum.LUCKIEST);

        assertEquals(p2.getAwards().size(), 1);
        assertEquals(p2.getAwards().get(0).isSoleWinner(), true);
    }

    @Test
    public void testTie() {
        // Money, chance tiles landed on, and number of minigames won is equal
        // All players should share the award

        int count = 1;
        for (AwardEnum award : AwardEnum.values()) {
            playerController.giveAward(award);

            assertEquals(p1.getAwards().size(), count);
            assertEquals(p1.getAwards().get(0).isSoleWinner(), false);

            count++;
        }

    }

}
