package window;

import core.AbstractWindow;
import core.ViewHandler;
import window.gameboard.GameboardController;
import window.gameboard.GameboardWindow;
import window.start.ConfigurationWindow;
import window.start.ConfigurationController;
import window.start.StartController;
import window.start.StartWindow;

import java.util.ResourceBundle;

public class WindowFactory {

    public AbstractWindow createWindow(String windowName, ViewHandler viewHandler, ResourceBundle bundle) {
        if (windowName == null) {
            return null;
        }

        if ("START".equals(windowName)) {
            return new StartWindow(new StartController(viewHandler), bundle);
        } else if ("TEAM_SELECTION".equals(windowName)) {
            return new ConfigurationWindow(new ConfigurationController(viewHandler), bundle);
        } else if ("GAMEBOARD".equals(windowName)) {
            return new GameboardWindow(new GameboardController(viewHandler), bundle);
        }

        return null;
    }


}
