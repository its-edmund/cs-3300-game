package tile;

import core.AppViewHandler;
import core.ResizableStackPane;
import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import token.Token;
import window.gameboard.GameboardController;

import java.util.ArrayList;
import java.util.Iterator;

import static tile.TileType.*;

public class Tile extends ResizableStackPane {

    private double width;
    private double height;

    private TileType type;

    private Rectangle rectangle;
    private Text rectangleText;
    private ArrayList<Token> tokenArr;
    private Wall tileWall;

    // 12, 12
    public Tile (double posX, double posY, GameboardController gameboardController) {
        this(TileType.STANDARD, 12, 12, posX, posY, gameboardController);
    }

    public Tile (TileType type, double width, double height,
                 double posX, double posY, GameboardController gameboardController) {

        super();

        this.type = type;

        this.width = width;
        this.height = height;

        this.rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(Color.BURLYWOOD);
        rectangle.setStroke(Color.BLACK);

        // This rotates ALL children of the Tile
        this.setRotate(45);

        this.rectangleText = new Text();
        rectangleText.setFont(new Font(10));

//        if (gameboardController.getViewHandler() != null) {
//            ChangeListener<Number> stageWidthListener = (observable, oldVal, newVal) -> {
//                this.relocate(posX, posY, gameboardController);
//            };
//            ChangeListener<Number> stageHeightListener = (observable, oldVal, newVal) -> {
//                this.relocate(posX, posY, gameboardController);
//            };
//            gameboardController.getViewHandler().addEventOnScreenWidthChange(stageWidthListener);
//            gameboardController.getViewHandler().addEventOnScreenHeightChange(stageHeightListener);
//        }

        this.getChildren().addAll(rectangle, rectangleText);

        tokenArr = new ArrayList<>();
    }

    public void relocate(double x, double y,
                         GameboardController gameboardController) {

        gameboardController.repositionChild(x, y, this);

        rectangle.setScaleX(gameboardController.getViewHandler().getScreenDimensions()[1] / 400);
        rectangle.setScaleY(gameboardController.getViewHandler().getScreenDimensions()[1] / 400);

        if (this.getChildren().size() > 2) {
            for (Object obj : this.getChildren()) {
                if (obj instanceof Token) {
                     Token token = (Token) obj;

                     token.setScaleX(gameboardController.getViewHandler().getScreenDimensions()[1] / 400);
                     token.setScaleY(gameboardController.getViewHandler().getScreenDimensions()[1] / 400);
                } else if (obj instanceof Wall) {
                    Wall wallStackPane = ((Wall) obj);
                    Rectangle wall = wallStackPane.getWall();
                    wall.setScaleX(gameboardController.getViewHandler().getScreenDimensions()[1] / 400);
                    wall.setScaleY(gameboardController.getViewHandler().getScreenDimensions()[1] / 400);

                    wallStackPane.relocate(this);
                }
            }
        }

    }

    // For Walls
    public void addWall(WallOrientationEnum orientation) {

        Wall wall = new Wall(this, orientation);

        if (tileWall == null) {
            this.getChildren().addAll(wall);
            tileWall = wall;
        } else {
            throw new RuntimeException("Trying to assign a wall to a tile that already " +
                    "has a wall!\n");
        }

    }

    public void addWall(double rot) {
        Wall wall = new Wall(this, WallOrientationEnum.TOP);

        wall.setRotate(rot);

        if (tileWall == null) {
            this.getChildren().addAll(wall);
            tileWall = wall;
        } else {
            throw new RuntimeException("Trying to assign a wall to a tile that already " +
                    "has a wall!\n");
        }
    }

    public void removeWall() {

        if (tileWall != null) {
            this.getChildren().remove(tileWall);
            tileWall = null;
        } else {
            throw new RuntimeException("Trying to remove a wall from a tile that " +
                    "doesn't have a wall!\n");
        }
    }
    public boolean hasWall() {
        return this.tileWall != null;
    }
    public Wall getWall() {
        return this.tileWall;
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
    public boolean hasToken() {
        return !tokenArr.isEmpty();
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
    public double getDefaultWidth() {
        return width;
    }
    public double getDefaultHeight() {
        return height;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public TileType getType() {
        return type;
    }
    public void setType(TileType tileType) {
        this.type = tileType;

        switch (type) {
            case CHANCE:
                rectangle.setFill(Color.AQUA);
                break;
            case GAIN_MONEY:
                rectangle.setFill(Color.LIGHTGREEN);
                break;
            case LOSE_MONEY:
                rectangle.setFill(Color.DARKRED);
                break;
            case START:
                rectangle.setFill(Color.YELLOW);
                break;
            case END:
                rectangle.setFill(Color.YELLOW);
                break;
            case LAUNCH_MINIGAME:
                rectangle.setFill(Color.DARKBLUE);
                break;
        }


    }
    public String getText() {
        return rectangleText.getText();
    }
    public void setText(String text) {
        rectangleText.setText(text);
    }
}
