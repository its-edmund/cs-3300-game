package tile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private int X_COORDINATE;
    private int Y_COORDINATE;

    private TileType type;

    public Tile (int X_COORDINATE, int Y_COORDINATE) {
        this(TileType.STANDARD, X_COORDINATE, Y_COORDINATE);
    }

    public Tile (TileType type, int X_COORDINATE, int Y_COORDINATE) {
        super(50, 50);
        this.type = type;
        this.setStroke(Color.BLACK);

        switch (type) {
            case END:
                this.setFill(Color.GREEN);
                break;

            case START:
                this.setFill(Color.BLUE);
                break;

            case STANDARD:
                this.setFill(Color.BURLYWOOD);
                break;
        }
        this.X_COORDINATE = X_COORDINATE;
        this.Y_COORDINATE = Y_COORDINATE;
    }
    public TileType getType() {
        return type;
    }
}
