package NotificationWindow.ChanceCard;

import NotificationWindow.AbstractNotificationController;
import core.AppViewHandler;
import core.Resizable;
import core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ChanceCard extends AbstractNotificationController implements Resizable {

    @FXML Rectangle promptBox;
    @FXML StackPane notificationWindow;

    private final double WIDTH = 125;
    private final double HEIGHT = 50;

    private final int NUM_CHANCE_CARDS = 3;
    private final int SEED = -1;

    private String[] chanceCardTextDescriptions;

    public ChanceCard(ViewHandler viewHandler) {
        super(viewHandler);

//
//        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//                System.out.println("Hello World");
//            }
//        };
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
//        System.out.println("Reached the constructor of ChanceCard!");

        promptBox.setWidth(WIDTH);
        promptBox.setHeight(HEIGHT);
    }

    public void setActive(boolean active) {
//        this.setVisible(active);
    }

    public boolean getActive() {
//        return this.isVisible();
        return false;
    }

    @Override
    public void onResize() {

    }

//    @Override
//    public void onResize() {
//
//        // Resize the box
////        promptBox.setScaleX(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);
////        promptBox.setScaleY(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_WIDTH);
//
//
//    }
}
