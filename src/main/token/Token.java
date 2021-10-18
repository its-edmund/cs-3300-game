package token;

import core.AppViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Token extends Circle {

    int tokenLocation;
    final int radius = 3;
    boolean isFinished;

    public Token(Color color, AppViewHandler viewHandler) {

        super();

        this.setStroke(Color.BLACK);
        this.setFill(color);
        this.setRadius(radius);

        this.tokenLocation = 0;

        isFinished = false;
    }

    public int getTokenLocation() {
        return tokenLocation;
    }

    public int getTokenRadius() {
        return radius;
    }

    public void setTokenLocation(int tokenLocation) {
        this.tokenLocation = tokenLocation;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean getFinished() {
        return isFinished;
    }
}
