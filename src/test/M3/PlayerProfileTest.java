package M3;

import javafx.scene.paint.Color;
import org.junit.Test;
import window.gameboard.PlayerProfile;
import window.player.Player;

import static org.junit.Assert.assertEquals;

public class PlayerProfileTest {


    @Test
    public void testPlayerProfile() {
        Player player = new Player("placeholder", Color.WHITE, 50, null);
        PlayerProfile playerProfile = new PlayerProfile(player);

        assertEquals(playerProfile.getPlayerProfileText().getText(),
                "placeholder\n" + "$50");
    }
}
