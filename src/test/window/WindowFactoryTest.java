package window;

import core.AbstractWindow;
import core.ViewHandler;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class WindowFactoryTest {

    @Test
    public void createWindowTest() {

        WindowFactory windowFactory = new WindowFactory();

        for (ScreenEnum screen : ScreenEnum.values()) {
            AbstractWindow window = windowFactory.createWindow(screen, null, null);
            System.out.println("Testing " + screen.toString());
            assertNotEquals(window, null);
        }
    }
}
