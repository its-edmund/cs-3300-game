package token;

import core.AppPaths;
import core.SVGShapes;
import javafx.scene.paint.Color;

public enum TokenEnum {

    DOG(Color.RED, SVGShapes.DOG, "puppy.wav", 0.02, 0.02),
    CAR(Color.BLUE, SVGShapes.CAR, "car_horn.wav", 0.4, 0.4),
    BOAT(Color.YELLOW, SVGShapes.BOAT, "foghorn.wav", 0.05, 0.05),
    PLANE(Color.GREEN, SVGShapes.PLANE, "plane.wav", 0.4, 0.4);
//    QUESTION_MARK(Color.BLACK, SVGShapes.QUESTION_MARK, "", 1, 1);

    private Color color;
    private String shape;
    private String filename;
    private double scaleX;
    private double scaleY;

    TokenEnum(Color color, String shape, String filename, double scaleX, double scaleY) {
        this.color = color;
        this.shape = shape;
        this.filename = filename;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public Color getColor() {
        return color;
    }
    public String getSVGShape() {
        return shape;
    }
    public String getSoundFilepath() {
        return AppPaths.SOUND_PATH + filename;
    }
    public double getScaleX() {
        return scaleX;
    }
    public double getScaleY() {
        return scaleY;
    }
}
