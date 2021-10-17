package window.gameboard;

import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ChanceCard extends StackPane {

    private ArrayList<Rectangle> chanceCards;
    private Rectangle movingChanceCard;
    private Label movingChanceCardText;

    private final int NUM_CHANCE_CARDS = 3;

    private final double WIDTH = 50;
    private final double HEIGHT = 85;

    private final double posX = 0.5;
    private final double posY = 0.5;

    public ChanceCard(ViewHandler viewHandler) {
        chanceCards = new ArrayList<>();

        for (int i = 0; i <= NUM_CHANCE_CARDS; i++) {
            Rectangle rectangle = new Rectangle();

            rectangle.setWidth(WIDTH);
            rectangle.setHeight(HEIGHT);
            rectangle.setFill(Color.AQUA);
            rectangle.setStrokeWidth(2);
            rectangle.setStroke(Color.BLACK);

            rectangle.setTranslateX(1 * i * 5);
            rectangle.setTranslateY(-1 * i * 5);


            if (i < NUM_CHANCE_CARDS) {
                chanceCards.add(rectangle);
                this.getChildren().add(rectangle);
            } else {
                StackPane movingChanceCardStackPane
                        = new StackPane();
                movingChanceCardStackPane.setTranslateX(0);
                movingChanceCardStackPane.setTranslateY(0);

                movingChanceCard = rectangle;
//                movingChanceCard.setVisible(true);
                movingChanceCard.setRotate(90);
                movingChanceCard.setTranslateX(0);
                movingChanceCard.setTranslateY(0);

                movingChanceCardText = new Label();
                movingChanceCardText.setText("Sample text\n");

                movingChanceCardStackPane.getChildren().addAll(movingChanceCard, movingChanceCardText);
                movingChanceCardStackPane.setVisible(false);

                this.getChildren().add(movingChanceCardStackPane);
            }

        }

        ChangeListener<Number> stageWidthListener = (observable, oldVal, newVal) -> {
            relocate(viewHandler);
        };
        ChangeListener<Number> stageHeightListener = (observable, oldVal, newVal) -> {
            relocate(viewHandler);
        };

        viewHandler.addEventOnScreenWidthChange(stageWidthListener);
        viewHandler.addEventOnScreenHeightChange(stageHeightListener);

    }

    public void relocate(ViewHandler viewHandler) {
        double screenWidth = (viewHandler.getScreenDimensions()[0] - 16);
        double screenHeight = (viewHandler.getScreenDimensions()[1] - 40 - 85);

        double newX = posX * screenHeight + (screenWidth - screenHeight) / 2;
        double newY = posY * screenHeight;

        super.relocate(newX, newY);
        this.setTranslateX((-1 * WIDTH) / 2.0);
        this.setTranslateY((-1 * HEIGHT) / 2.0);

        this.setWidth(WIDTH * viewHandler.getScreenDimensions()[1] / 400);
        this.setHeight(HEIGHT * viewHandler.getScreenDimensions()[1] / 400);

        for (Rectangle rectangle : chanceCards) {
            rectangle.setScaleX(viewHandler.getScreenDimensions()[1] / 400);
            rectangle.setScaleY(viewHandler.getScreenDimensions()[1] / 400);
        }

        movingChanceCard.setScaleX(1.3 * viewHandler.getScreenDimensions()[1] / 400);
        movingChanceCard.setScaleY(1.3* viewHandler.getScreenDimensions()[1] / 400);
        movingChanceCardText.scaleXProperty().set(1 * viewHandler.getScreenDimensions()[1] / 400);
        movingChanceCardText.scaleYProperty().set(1 * viewHandler.getScreenDimensions()[1] / 400);
    }

    public void showChanceCard() {

    }

}
