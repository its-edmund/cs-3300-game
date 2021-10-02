package window.start;

import core.AbstractWindow;

import java.util.ResourceBundle;

public class StartWindow extends AbstractWindow {

    public StartWindow(StartController startController, ResourceBundle bundle) {
        super(startController, bundle);
    }

    @Override
    protected String iconFileName() {
        return "startIcon.png";
    }

    @Override
    protected String fxmlFileName() {
        return "start_view.fxml";
    }

    @Override
    public String titleBundleKey() {
        return "start.title";
    }
}
