package window;

import core.AbstractWindow;
import core.AppViewHandler;
import core.ViewHandler;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class WindowFactoryTest {

    /**
     * Author: Andrew Roach
     * Milestone: M2
     * Purpose: unit test for the createWindowTest method. Ensures that the factory doesn't return a
     * null screen.
     */
    @Test
    public void createWindowTest() {

        WindowFactory windowFactory = new WindowFactory();
        AppViewHandler viewHandler = new AppViewHandler(null, null);

        for (ScreenEnum screen : ScreenEnum.values()) {
            AbstractWindow window = windowFactory.createWindow(screen, viewHandler, null);
            System.out.println("Testing " + screen.toString());
            assertNotEquals(window, null);
        }
    }
}
