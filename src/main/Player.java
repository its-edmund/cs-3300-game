import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public class Player {

    // For now, player representation is a circle
    private Circle view;
    private static float radius = 20.0f;
    private float x;
    private float y;

    public Player(float x, float y) {
        this.view = new Circle();
        view.setCenterX(x);
        view.setCenterY(y);
        view.setRadius(this.radius);
        this.x = x;
        this.y = y;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
        view.setCenterX(x);
        view.setCenterY(y);
    }

    public void draw(Scene scene) {

    }
}
