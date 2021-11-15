package M5;

import core.TestViewHandler;
import org.junit.Test;
import Minigame.SugarHoneycombMinigame;
import Minigame.TheCircleMinigame;
import static junit.framework.TestCase.assertEquals;

public class FinishMiniGameTests {

    @Test
    public void testSugarHoneycombFinish() {
        SugarHoneycombMinigame minigame = new SugarHoneycombMinigame();
        TestViewHandler vh = minigame.getViewHandler();
        minigame.testEndGame(vh);
        assertEquals(vh.getState(), 1);
    }

    @Test
    public void testCircleFinish() {
        TheCircleMinigame minigame = new TheCircleMinigame();
        TestViewHandler vh = minigame.getViewHandler();
        minigame.testEndGame(vh);
        assertEquals(vh.getState(), 1);
    }
}
