package window.start;

import core.AbstractWindow;

import java.util.ResourceBundle;

public class ConfigurationWindow extends AbstractWindow {

    public ConfigurationWindow(ConfigurationController configController, ResourceBundle bundle) {
        super(configController, bundle);
    }

    @Override
    protected String iconFileName() {
        return "startIcon.png";
    }

    @Override
    protected String fxmlFileName() {
        return "config_view.fxml";
    }

    @Override
    public String titleBundleKey() {
        return "start.title";
    }
}
