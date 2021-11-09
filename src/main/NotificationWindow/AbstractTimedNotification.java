package NotificationWindow;

import core.ViewHandler;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

public abstract class AbstractTimedNotification extends AbstractNotification {

    public AbstractTimedNotification(ViewHandler viewHandler, double time) {
        super(viewHandler);

        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished(event -> onExit());

        delay.play();
    }

}
