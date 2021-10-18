package M3;

import javafx.scene.paint.Color;
import org.junit.Test;
import window.gameboard.GameStateController;
import window.gameboard.GameboardController;
import window.gameboard.GameboardWindow;
import window.gameboard.PlayerProfile;
import window.player.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PlayerOutputTests {


    @Test
    public void testPlayerProfile() {
        Player player = new Player("placeholder", Color.WHITE, 50, null);
        PlayerProfile playerProfile = new PlayerProfile(player);

        assertEquals(playerProfile.getPlayerProfileText().getText(),
                "placeholder\n" + "$50");
    }

    @Test
    public void changePlayerStatusInvalidInput() {
        GameboardController gameboardController = new GameboardController(null);

        assertThrows(IllegalArgumentException.class, () -> {
            gameboardController.changePlayerStatus(-1);
        });
    }
}
