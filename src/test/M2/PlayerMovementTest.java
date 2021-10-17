package M2;

import window.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerMovementTest {
    @Test
    public void movePlayerTest() {
        Player player = new Player();
//        player.setupPlayerMover(null);

        assertNull(player.getToken());
//        for (int i = 0; i < 15; i++) {
//            player.getToken().setTokenLocation(i);
//            assertEquals(i, player.getToken().getTokenLocation());
//        }

//        for (int i = -10; i < -5; i++) {
//            player.getToken().setTokenLocation(i);
//            assertEquals(14, player.getToken().getTokenLocation());
//        }
//
//        for (int i = 20; i < 30; i++) {
//            player.getToken().setTokenLocation(i);
//            assertEquals(14, player.getToken().getTokenLocation());
//        }
    }
}
