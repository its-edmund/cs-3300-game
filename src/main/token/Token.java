package token;

import core.AppViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Token extends Circle {

    private int tokenLocation;
    private final int RADIUS = 3;
    private boolean isFinished;

    public Token(Color color, AppViewHandler viewHandler) {

        super();

        this.setStroke(Color.BLACK);
        this.setFill(color);
        this.setRadius(RADIUS);

        this.tokenLocation = 0;

        isFinished = false;
    }

    // Getters and Setters
    public int getTokenLocation() {
        return tokenLocation;
    }
    public int getTokenRadius() {
        return RADIUS;
    }
    public boolean getFinished() {
        return isFinished;
    }

    public void setTokenLocation(int tokenLocation) {
        this.tokenLocation = tokenLocation;
    }
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
}
