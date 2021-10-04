package window;

import core.AbstractWindow;
import core.ViewHandler;
import window.gameboard.GameboardController;
import window.gameboard.GameboardWindow;
import window.config_screen.ConfigurationWindow;
import window.config_screen.ConfigurationController;
import window.start.StartController;
import window.start.StartWindow;

import java.util.ResourceBundle;

public class WindowFactory {

    public AbstractWindow createWindow(ScreenEnum screenName, ViewHandler viewHandler, ResourceBundle bundle) {

        if (screenName == ScreenEnum.START) {
            return new StartWindow(new StartController(viewHandler), bundle);
        } else if (screenName == ScreenEnum.CONFIG) {
            return new ConfigurationWindow(new ConfigurationController(viewHandler), bundle);
        }else if (screenName == ScreenEnum.GAMEBOARD) {
            return new GameboardWindow(new GameboardController(viewHandler), bundle);
        }
        return null;
    }
}
