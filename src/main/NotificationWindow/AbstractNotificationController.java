package NotificationWindow;

import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractNotificationController implements Initializable {
    protected final ViewHandler viewHandler;

    public AbstractNotificationController(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    @Override
    public abstract void initialize(URL location, ResourceBundle bundle);

    public abstract boolean getActive();
    public abstract void setActive(boolean active);
}
