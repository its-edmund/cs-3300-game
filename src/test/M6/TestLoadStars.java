package M6;

import org.junit.Test;
import window.victory_screen.VictoryScreenController;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class TestLoadStars {

    @Test
    public void testLoadStars() throws FileNotFoundException {

        VictoryScreenController victoryScreenController = new VictoryScreenController(null);
        victoryScreenController.loadStars();
    }

}
