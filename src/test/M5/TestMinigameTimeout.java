package M5;

import Minigame.ExampleMinigame2;
import Minigame.SugarHoneycombMinigame;
import Minigame.TheCircleMinigame;
import core.TestViewHandler;
import core.ViewHandler;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestMinigameTimeout {
    @Test
    public void testSugarHoneycombFinish() throws InterruptedException {
        SugarHoneycombMinigame minigame = new SugarHoneycombMinigame();
        TestViewHandler vh = minigame.getViewHandler();
        assertEquals(vh.getState(), 0);
        Thread.sleep(5000);
        minigame.testEndGame(vh);
        assertEquals(vh.getState(), 1);
    }

    @Test
    public void testCircleFinish() throws InterruptedException {
        TheCircleMinigame minigame = new TheCircleMinigame();
        TestViewHandler vh = minigame.getViewHandler();
        assertEquals(vh.getState(), 0);
        Thread.sleep(5000);
        minigame.testEndGame(vh);
        assertEquals(vh.getState(), 1);
    }
}
