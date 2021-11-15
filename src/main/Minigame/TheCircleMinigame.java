package Minigame;

import core.GameStates;
import core.ViewHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TheCircleMinigame {

    private ViewHandler viewHandler;
    private ExampleMinigame2 minigame;
    private Timeline timer;

    double currentTime = 5.0;

    public TheCircleMinigame(ViewHandler viewHandler, ExampleMinigame2 minigame) {
        this.viewHandler = viewHandler;
        this.minigame = minigame;

        pattern1();
    }

    public void pattern1() {
        double timeLimit = 5.0;

        timer = new Timeline(
                new KeyFrame(Duration.seconds(0.1),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                currentTime -= 0.1;

                                if (currentTime <= 0) {
                                    viewHandler.getState().getPlayerController().
                                            getCurrentMinigamePlayer().setMinigameScore(0);
                                    viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);
                                }
                            }
                        }));
        timer.setCycleCount(Animation.INDEFINITE);

        MinigameCircle circle1 =  new MinigameCircle(true, false, 50, 0);
        MinigameCircle circle2 =  new MinigameCircle(false, false, -50, 0);
        MinigameCircle circle3 =  new MinigameCircle(false, false, 30, 40);
        MinigameCircle circle4 =  new MinigameCircle(false, false, -30, -40);
        MinigameCircle circle5 =  new MinigameCircle(false, false, 0, 50);
        MinigameCircle circle6 =  new MinigameCircle(false, true, 0, -50);

        circle1.setNextCircle(circle2);
        circle2.setNextCircle(circle3);
        circle3.setNextCircle(circle4);
        circle4.setNextCircle(circle5);
        circle5.setNextCircle(circle6);
        circle6.setNextCircle(null);

        minigame.getChildren().addAll(circle1, circle2, circle3, circle4, circle5, circle6);
    }

    private class MinigameCircle extends Circle {

        private MinigameCircle next;
        private boolean isFirst;
        private boolean isLast;

        public MinigameCircle(boolean isFirst, boolean isLast) {
            super();

            this.isFirst = isFirst;
            this.isLast = isLast;

            this.setRadius(10);
            this.setFill(Color.ORANGERED);
            this.setStroke(Color.BLACK);

            if (isFirst) {
                this.setVisible(true);
            } else {
                this.setVisible(false);
            }
        }

        public MinigameCircle(boolean isFirst, boolean isLast, double x, double y) {
            this(isFirst, isLast);

            this.setTranslateX(x);
            this.setTranslateY(y);
        }

        public void setNextCircle(MinigameCircle nextCircle) {
            this.next = nextCircle;

            this.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, event -> {
                if (this.isVisible() &&
                        viewHandler.getState().getCurrentState() == GameStates.MINIGAME_PLAY) {

                    if (isLast) {
                        timer.stop();

                        viewHandler.getState().getPlayerController().
                                getCurrentMinigamePlayer().setMinigameScore(currentTime * 100);

                        this.setVisible(false);

                        viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);

                    } else {
                        // upon hovering over the first circle, start the timer
                        if (isFirst) {
                            // start the timer
                            timer.play();
                        }

                        this.setVisible(false);
                        next.setVisible(true);
                    }
                }
            });
        }
    }

}
