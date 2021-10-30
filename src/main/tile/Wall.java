package tile;

import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends StackPane {

    private Rectangle wall;
    private WallOrientationEnum orientation;

    private final double WIDTH = 2;
    private final double HEIGHT = 15;

    public Wall(Tile tile, WallOrientationEnum orientation) {
        super();

        this.wall = new Rectangle();
        wall.setWidth(WIDTH);
        wall.setHeight(HEIGHT);

        wall.setRotate(tile.getRectangle().getRotate());

        wall.setFill(Color.GRAY);
        wall.setStroke(Color.BLACK);

        setOrientation(orientation);

        this.getChildren().add(wall);
    }

    public Rectangle getWall() {
        return this.wall;
    }

    public void setActive(boolean isActive) {
        this.setVisible(isActive);
    }
    public boolean isActive() {
        return this.isVisible();
    }

    public void setOrientation(WallOrientationEnum orientation) {
        this.orientation = orientation;
    }
    public WallOrientationEnum getOrientation() {
        return this.orientation;
    }

    public void relocate(Tile tile) {
        if (orientation == WallOrientationEnum.TOP) {
            wall.setRotate(90);
        } else if (orientation == WallOrientationEnum.BOTTOM) {
            wall.setRotate(90);
        } else if  (orientation == WallOrientationEnum.LEFT) {
            wall.setRotate(0);
        } else if (orientation == WallOrientationEnum.RIGHT){
            wall.setRotate(0);
        } else {
            throw new RuntimeException("Wall's orientation not set!");
        }
    }
}
