package window.gameboard;

import core.AbstractController;
import core.AbstractWindow;

import java.util.ResourceBundle;

public class GameboardWindow extends AbstractWindow {

    public GameboardWindow(AbstractController controller, ResourceBundle bundle) {
        super(controller, bundle);
    }

    @Override
    protected String iconFileName() {
        return "startIcon.png";
    }

    @Override
    protected String fxmlFileName() {
        return "gameboard_view.fxml";
    }

    @Override
    public String titleBundleKey() {
        return "start.title";
    }
}
