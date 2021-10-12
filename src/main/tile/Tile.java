package tile;

import core.AppViewHandler;
import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private double X_COORDINATE;
    private double Y_COORDINATE;
    private double width;
    private double height;

    private double posX;
    private double posY;

    private TileType type;

    private Rectangle rectangle;
    private Text rectangleText;

    public Tile (double X_COORDINATE, double Y_COORDINATE, AppViewHandler viewHandler) {
        this(TileType.STANDARD, X_COORDINATE, Y_COORDINATE, 12, 12, viewHandler);
    }

    public Tile (TileType type, double X_COORDINATE, double Y_COORDINATE, double width, double height, AppViewHandler viewHandler) {

        super();

        this.type = type;

        this.X_COORDINATE = X_COORDINATE;
        this.Y_COORDINATE = Y_COORDINATE;
        this.width = width;
        this.height = height;
        this.posX = X_COORDINATE;
        this.posY = Y_COORDINATE;

        this.rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(Color.BURLYWOOD);
        rectangle.setStroke(Color.BLACK);
        rectangle.setRotate(45);

//        switch (type) {
//            case END:
//                rectangle.setFill(Color.RED);
//                break;
//
//            case START:
//                rectangle.setFill(Color.GREEN);
//                break;
//
//            case STANDARD:
//                rectangle.setFill(Color.BURLYWOOD);
//                break;
//        }

        this.rectangleText = new Text();
        rectangleText.setFont(new Font(10));

        ChangeListener<Number> stageWidthListener = (observable, oldVal, newVal) -> {
            this.relocate(posX, posY, viewHandler);
        };
        ChangeListener<Number> stageHeightListener = (observable, oldVal, newVal) -> {
            this.relocate(posX, posY, viewHandler);
        };
        viewHandler.addEventOnScreenWidthChange(stageWidthListener);
        viewHandler.addEventOnScreenHeightChange(stageHeightListener);

        this.getChildren().addAll(rectangle, rectangleText);
    }

    public TileType getType() {
        return type;
    }


    public String getText() {
        return rectangleText.getText();
    }

    public void setText(String text) {
        rectangleText.setText(text);
    }

    public void relocate(double x, double y, AppViewHandler viewHandler) {

        double screenWidth = (viewHandler.getScreenDimensions()[0] - 16);
        double screenHeight = (viewHandler.getScreenDimensions()[1] - 40 - 85);

        // 16: width offscreen
        double newX = x * screenHeight + (screenWidth - screenHeight) / 2;
        // 40: height offscreen
        // 85: height of HUD
        double newY = y * screenHeight;

        super.relocate(newX, newY);
        this.setTranslateX((-1 * width) / 2.0);
        this.setTranslateY((-1 * height) / 2.0);

        rectangle.setWidth(width * viewHandler.getScreenDimensions()[1] / 400);
        rectangle.setHeight(height * viewHandler.getScreenDimensions()[1] / 400);

//        rectangle.setWidth(width * viewHandler.getScreenDimensions()[0] / viewHandler.INITIAL_SCREEN_WIDTH);
//        rectangle.setHeight(height * viewHandler.getScreenDimensions()[1] / viewHandler.INITIAL_SCREEN_HEIGHT);
    }
}
