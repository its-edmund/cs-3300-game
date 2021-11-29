package token;

import core.AppPaths;
import core.SVGShapes;
import javafx.scene.paint.Color;

public enum TokenEnum {
    DOG(Color.RED, SVGShapes.DOG, "puppy.wav"),
    CAR(Color.BLUE, SVGShapes.CAR, "car_horn.wav"),
    BOAT(Color.YELLOW, SVGShapes.BOAT, "foghorn.wav"),
    PLANE(Color.GREEN, SVGShapes.PLANE, "plane.wav");

    private Color color;
    private String shape;
    private String filename;

    TokenEnum(Color color, String shape, String filename) {
        this.color = color;
        this.shape = shape;
        this.filename = filename;
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
}
