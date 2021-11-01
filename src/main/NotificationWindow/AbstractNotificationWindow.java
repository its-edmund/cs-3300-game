package NotificationWindow;

import core.AbstractController;
import core.AppPaths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractNotificationWindow {

    private final AbstractNotificationController controller;

    public AbstractNotificationWindow(AbstractNotificationController controller) {
        this.controller = controller;
    }

    public Parent getParent() {
        FXMLLoader loader = new FXMLLoader(url());
        loader.setController(controller);
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private URL url() {
        return getClass().getClassLoader().getResource(AppPaths.FXML_PATH + fxmlFileName());
    }

    protected abstract String fxmlFileName();
}
