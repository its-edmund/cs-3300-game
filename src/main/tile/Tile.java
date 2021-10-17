package tile;

import core.AppViewHandler;
import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import token.Token;

import java.util.ArrayList;
import java.util.Iterator;

import static tile.TileType.*;

public class Tile extends StackPane {

    private double width;
    private double height;

    private double posX;
    private double posY;

    private TileType type;

    private Rectangle rectangle;
    private Text rectangleText;
    private ArrayList<Token> tokenArr;

    // 12, 12
    public Tile (double posX, double posY, ViewHandler viewHandler) {
        this(TileType.STANDARD, 12, 12, posX, posY, viewHandler);
    }

    public Tile (TileType type, double width, double height, double posX, double posY, ViewHandler viewHandler) {

        super();

        this.type = type;

        this.width = width;
        this.height = height;

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

        if (viewHandler != null) {
            ChangeListener<Number> stageWidthListener = (observable, oldVal, newVal) -> {
                this.relocate(posX, posY, viewHandler);
            };
            ChangeListener<Number> stageHeightListener = (observable, oldVal, newVal) -> {
                this.relocate(posX, posY, viewHandler);
            };
            viewHandler.addEventOnScreenWidthChange(stageWidthListener);
            viewHandler.addEventOnScreenHeightChange(stageHeightListener);
        }

        this.getChildren().addAll(rectangle, rectangleText);

        tokenArr = new ArrayList<>();
    }



    public void relocate(double x, double y, ViewHandler viewHandler) {

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

//        if (this.getChildren().size() > 2) {
//            System.out.println("We don't have orphans!");
//        }
        if (this.getChildren().size() > 2) {
//            System.out.println(this.getChildren().get(this.getChildren().size() - 1).toString());
            for (Object obj : this.getChildren()) {
                if (obj instanceof Token) {
                     Token token = (Token) obj;
                     token.setRadius(token.getTokenRadius() * viewHandler.getScreenDimensions()[1] / 400);
                }

            }
        }

//        rectangle.setWidth(width * viewHandler.getScreenDimensions()[0] / viewHandler.INITIAL_SCREEN_WIDTH);
//        rectangle.setHeight(height * viewHandler.getScreenDimensions()[1] / viewHandler.INITIAL_SCREEN_HEIGHT);
    }

    // For Tokens on the Tiles
    public void addToken(Token token) {
        this.getChildren().add(token);
        tokenArr.add(token);
        layoutTokens();
    }
    public void removeToken(Token token) {
        this.getChildren().remove(token);
        tokenArr.remove(token);
        layoutTokens();
    }
    public void layoutTokens() {
        if (tokenArr.size() == 1) {
            tokenArr.get(0).setTranslateX(0);
            tokenArr.get(0).setTranslateY(0);
        } else if (tokenArr.size() == 2) {
            tokenArr.get(0).setTranslateX(0);
            tokenArr.get(0).setTranslateY(5);
            tokenArr.get(1).setTranslateX(0);
            tokenArr.get(1).setTranslateY(-5);
        } else if (tokenArr.size() == 3) {
            tokenArr.get(0).setTranslateX(0);
            tokenArr.get(0).setTranslateY(5);
            tokenArr.get(1).setTranslateX(3);
            tokenArr.get(1).setTranslateY(-3);
            tokenArr.get(2).setTranslateX(-3);
            tokenArr.get(2).setTranslateY(-3);
        } else if (tokenArr.size() == 4) {
            tokenArr.get(0).setTranslateX(0);
            tokenArr.get(0).setTranslateY(5);
            tokenArr.get(1).setTranslateX(0);
            tokenArr.get(1).setTranslateY(-5);
            tokenArr.get(2).setTranslateX(5);
            tokenArr.get(2).setTranslateY(0);
            tokenArr.get(2).setTranslateX(-5);
            tokenArr.get(2).setTranslateY(0);
        }
    }

    // Getters and Setters
    public Rectangle getRectangle() {
        return rectangle;
    }
    public TileType getType() {
        return type;
    }
    public void setType(TileType tileType) {
        this.type = tileType;

        if (type == CHANCE) {
            rectangle.setFill(Color.AQUA);
        } else if (type == GAIN_MONEY) {
            rectangle.setFill(Color.LIGHTGREEN);
        } else if (type == LOSE_MONEY) {
            rectangle.setFill(Color.DARKRED);
        } else if (type == START) {
            rectangle.setFill(Color.YELLOW);
        }
    }
    public String getText() {
        return rectangleText.getText();
    }
    public void setText(String text) {
        rectangleText.setText(text);
    }
}
