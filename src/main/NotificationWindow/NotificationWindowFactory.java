package NotificationWindow;

import NotificationWindow.ChanceCard.ChanceCard;
import NotificationWindow.ChanceCard.ChanceCardWindow;
import core.ViewHandler;

public class NotificationWindowFactory {

    public AbstractNotificationWindow createNotificationWindow(NotificationScreenEnum notificationScreenEnum, ViewHandler viewHandler) {
        if (notificationScreenEnum == NotificationScreenEnum.CHANCE_CARD) {
            return new ChanceCardWindow(new ChanceCard(viewHandler));
        }
        return null;
    }
}
