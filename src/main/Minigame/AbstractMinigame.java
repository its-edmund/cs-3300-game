package Minigame;

import NotificationWindow.AbstractNotification;
import core.ResizableStackPane;
import core.ViewHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class AbstractMinigame extends ResizableStackPane {

    protected ViewHandler viewHandler;

    protected Rectangle minigameScreen;

    private double WIDTH = 200;
    private double HEIGHT = 100;

    public AbstractMinigame(ViewHandler viewHandler) {

        super();

        this.viewHandler = viewHandler;

        minigameScreen = new Rectangle();
        minigameScreen.setWidth(WIDTH);
        minigameScreen.setHeight(HEIGHT);
        minigameScreen.setFill(Color.WHITE);
        minigameScreen.setStroke(Color.BLACK);

        this.getChildren().add(minigameScreen);
    }

    public abstract String getMinigameTitle();

    public abstract String getMinigameDescription();

    public abstract void onExit();

}
