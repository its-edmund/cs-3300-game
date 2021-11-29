package token;

import core.AppViewHandler;
import core.SVGShapes;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class Token extends SVGPath {

    private int tokenLocation;
    private boolean isFinished;

    private TokenEnum tokenType;

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
    public boolean getFinished() {
        return isFinished;
    }
    public TokenEnum getTokenType() {
        return tokenType;
    }

    public void setTokenLocation(int tokenLocation) {
        this.tokenLocation = tokenLocation;
    }
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    public void setTokenType(TokenEnum tokenType) {
        this.tokenType = tokenType;

        setupTokenShape();
    }

    private void setupTokenShape() {
        setContent(tokenType.getSVGShape());
    }

}
