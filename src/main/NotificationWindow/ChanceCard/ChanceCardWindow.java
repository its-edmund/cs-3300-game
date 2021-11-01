package NotificationWindow.ChanceCard;

import NotificationWindow.AbstractNotificationController;
import NotificationWindow.AbstractNotificationWindow;
import core.AbstractController;
import core.Resizable;

public class ChanceCardWindow extends AbstractNotificationWindow implements Resizable {

    public ChanceCardWindow(AbstractNotificationController controller) {
        super(controller);
    }

    @Override
    protected String fxmlFileName() {
        return "chance_card.fxml";
    }

    @Override
    public void onResize() {
        System.out.println("Resize me!");
    }
}
