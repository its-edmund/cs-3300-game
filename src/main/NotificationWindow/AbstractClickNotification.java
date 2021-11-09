package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public abstract class AbstractClickNotification extends AbstractNotification {

    public AbstractClickNotification(ViewHandler viewHandler) {
        super(viewHandler);

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                onExit();
            }
        };
        //Adding event Filter
        notificationBox.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

//    public void onExit() {
//        System.out.println("Exited clickable notification!");
//        viewHandler.getState().updateState(GameStates.END_TURN);
//    }

}
