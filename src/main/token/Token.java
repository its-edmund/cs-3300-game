package token;

import core.AppViewHandler;
import core.SVGShapes;
import javafx.beans.value.ChangeListener;
import javafx.css.Styleable;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import javax.sound.sampled.Control;

public class Token extends SVGPath {

    private int tokenLocation;
    private final int RADIUS = 3;
    private boolean isFinished;

    public Token(Color color, AppViewHandler viewHandler) {
        super();

        setContent(SVGShapes.CAR);
        setFill(color);
        setStroke(Color.BLACK);
        setScaleX(0.4);
        setScaleY(0.4);
        setRotate(-45);

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
