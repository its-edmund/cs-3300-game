package window.victory_screen;

import core.AbstractController;
import core.AbstractWindow;

import java.util.ResourceBundle;

public class VictoryScreenWindow extends AbstractWindow {

    public VictoryScreenWindow(AbstractController controller, ResourceBundle bundle) {
        super(controller, bundle);
    }

    @Override
    protected String iconFileName() {
        return "startIcon.png";
    }

    @Override
    protected String fxmlFileName() {
        return "victory_screen.fxml";
    }

    @Override
    public String titleBundleKey() {
        return "start.title";
    }
}
