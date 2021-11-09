package NotificationWindow;

import core.ResizableStackPane;
import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
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

        layoutBox = new VBox();
        layoutBox.setAlignment(Pos.CENTER);

        notificationBox = new Rectangle();
        notificationBox.setWidth(DEFAULT_WIDTH);
        notificationBox.setHeight(DEFAULT_HEIGHT);
        notificationBox.setFill(DEFAULT_FILL_COLOR);
        notificationBox.setStroke(DEFAULT_STROKE_COLOR);
        notificationBox.setStrokeWidth(3);

        notificationText = new Text();
        notificationText.setFont(new Font(DEFAULT_TEXT_SIZE));
        notificationText.setText(DEFAULT_TEXT);
        layoutBox.getChildren().add(notificationText);

        this.getChildren().addAll(notificationBox, layoutBox);
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
