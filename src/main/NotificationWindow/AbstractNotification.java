package NotificationWindow;

import core.ResizableStackPane;
import core.ViewHandler;
import core.ViewOrder;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public abstract class AbstractNotification extends ResizableStackPane {

    protected ViewHandler viewHandler;

    protected VBox layoutBox;
    protected Rectangle notificationBox;
    protected Text notificationText;

    private final double DEFAULT_WIDTH = 100;
    private final double DEFAULT_HEIGHT = 50;
    private final Color DEFAULT_FILL_COLOR = Color.LIGHTYELLOW;
    private final Color DEFAULT_STROKE_COLOR = Color.BLACK;
    private final double DEFAULT_TEXT_SIZE = 10;
    private final String DEFAULT_TEXT = "Example Text";

    public AbstractNotification(ViewHandler viewHandler) {
        super();

        this.viewHandler = viewHandler;

        this.setViewOrder(ViewOrder.NOTIFICATION);

        layoutBox = new VBox();
        layoutBox.setAlignment(Pos.CENTER);

        notificationBox = new Rectangle();
        notificationBox.setWidth(DEFAULT_WIDTH);
        notificationBox.setHeight(DEFAULT_HEIGHT);
        notificationBox.setFill(DEFAULT_FILL_COLOR);
        notificationBox.setStroke(DEFAULT_STROKE_COLOR);
        notificationBox.setStrokeWidth(3);

        this.getChildren().addAll(notificationBox, layoutBox);
    }

    public void setNotificationColor(Color color) {
        notificationBox.setFill(color);
    }
    public void setNotificationText(Node... nodes) {
        layoutBox.getChildren().addAll(nodes);
    }
    public void setNotificationWidth(double width) {
        notificationBox.setWidth(width);
    }
    public void setNotificationHeight(double height) {
        notificationBox.setHeight(height);
    }

    public abstract void onExit();

    // Getters
    public Rectangle getNotificationBox() {
        return notificationBox;
    }
    public Text getNotificationText() {
        return notificationText;
    }

}
