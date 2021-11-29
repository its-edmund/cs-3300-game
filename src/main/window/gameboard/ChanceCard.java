package window.gameboard;

import core.ResizableStackPane;
import core.SVGShapes;
import core.ViewHandler;
import core.ViewOrder;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import token.TokenEnum;
import token.TokenIcon;

import java.util.ArrayList;

public class ChanceCard extends ResizableStackPane {

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

        for (int i = 0; i < NUM_CHANCE_CARDS; i++) {

            int translateX = 1 * i * 5;
            int translateY = -1 * i * 5;

            Rectangle chanceCard = createChanceCard(translateX, translateY);

            if (i == NUM_CHANCE_CARDS - 1) {
                TokenIcon icon = createQuestionMark(translateX, translateY);

                this.getChildren().addAll(chanceCard, icon);

            }  else {
                chanceCards.add(chanceCard);
                this.getChildren().add(chanceCard);
            }
        }

    }

    private Rectangle createChanceCard(int translateX, int translateY) {
        Rectangle rectangle = new Rectangle();

        rectangle.setViewOrder(ViewOrder.BACKGROUND);

        rectangle.setWidth(WIDTH);
        rectangle.setHeight(HEIGHT);
        rectangle.setFill(Color.AQUA);
        rectangle.setStrokeWidth(2);
        rectangle.setStroke(Color.BLACK);

        rectangle.setTranslateX(translateX);
        rectangle.setTranslateY(translateY);

        return rectangle;
    }

    private TokenIcon createQuestionMark(int translateX, int translateY) {
        TokenIcon icon = new TokenIcon();
        icon.setTokenContent(SVGShapes.QUESTION_MARK);
        icon.setColor(Color.BLACK);
        icon.setSize(40, 40);

        icon.setTranslateX(translateX);
        icon.setTranslateY(translateY);

        return icon;
    }

    private void createRotatingChanceCard() {
        //                StackPane movingChanceCardStackPane
//                        = new StackPane();
//                movingChanceCardStackPane.setTranslateX(0);
//                movingChanceCardStackPane.setTranslateY(0);
//
//
//                movingChanceCard = chanceCard;
//                movingChanceCard.setRotate(90);
//                movingChanceCard.setTranslateX(0);
//                movingChanceCard.setTranslateY(0);
//
//                movingChanceCardText = new Label();
//                movingChanceCardText.setText("Sample text\n");
//
//                movingChanceCardStackPane.getChildren().addAll(movingChanceCard, movingChanceCardText);
//                movingChanceCardStackPane.setVisible(false);
//
//                this.getChildren().add(movingChanceCardStackPane);
    }

}
