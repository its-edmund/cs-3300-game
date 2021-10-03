package window.player;

import window.player.Player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerMovementTest {
    @Test
    public void movePlayerTest() {
        Player player = new Player();

        for (int i = 0; i < 15; i++) {
            player.setCurrentLocation(i);
            assertEquals(i, player.getCurrentLocation());
        }

        for (int i = -10; i < -5; i++) {
            player.setCurrentLocation(i);
            assertEquals(14, player.getCurrentLocation());
        }

        for (int i = 20; i < 30; i++) {
            player.setCurrentLocation(i);
            assertEquals(14, player.getCurrentLocation());
        }
    }
}
