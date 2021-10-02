package window.player;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Player extends Circle {

    // For now, player representation is a circle
    private static float radius = 20.0f;
    private int x;
    private int y;
    private int currentLocation;
    private int locationLimit = 15;

    public Player() {
        this(0, 0);
    }

    public Player(int x, int y) {
        super(x, y, radius);
        this.currentLocation = 0;
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        this.setCenterX(x);
        this.setCenterY(y);
    }

    public int getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        if (currentLocation >= locationLimit || currentLocation < 0) {
            return;
        }
        this.currentLocation = currentLocation;
    }

    public void setLocationLimit(int limit) {
        this.locationLimit = limit;
    }
}
