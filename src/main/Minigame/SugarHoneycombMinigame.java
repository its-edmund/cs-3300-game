package Minigame;

import core.GameStates;
import core.ViewHandler;
import core.TestViewHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class SugarHoneycombMinigame {

    private ViewHandler viewHandler;
    private ExampleMinigame2 minigame;
    private Timeline timer;

    private double currentTime;

    private double score = 0;
    private int tokenCount = 0;
    private int tokenTotal;

    public SugarHoneycombMinigame(ViewHandler viewHandler, ExampleMinigame2 minigame) {
        this.viewHandler = viewHandler;
        this.minigame = minigame;

        currentTime = 3.0;

        shape1();
    }
    public SugarHoneycombMinigame() {
        this.viewHandler = null;
        this.minigame = null;
        currentTime = 0.0;
    }
    public TestViewHandler getViewHandler() {
        currentTime = 60.0;
        return new TestViewHandler(0, currentTime);
    }
    public void testEndGame(TestViewHandler vh) {
        vh.setState(1);
    }
    public void shape1() {

        timer = new Timeline(
                new KeyFrame(Duration.seconds(0.1),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                currentTime -= 0.1;

                                if (currentTime <= 0) {
                                    timer.stop();
                                    cleanup();

                                    viewHandler.getState().getPlayerController().
                                            getCurrentMinigamePlayer().setMinigameScore(score);
                                    viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);
                                }
                            }
                        }));
        timer.setCycleCount(Animation.INDEFINITE);

        tokenTotal = 18;

        for (int y = -30; y < 30; y+=10) {
            int x = -30;
            Token token = new Token(x, y);
            token.setVisible(false);
            minigame.getChildren().add(token);
        }

        for (int y = -30; y < 30; y+=10) {
            int x = 30;
            Token token = new Token(x, y);
            token.setVisible(false);
            minigame.getChildren().add(token);
        }

        for (int x = -30, y = -30; x < 30 && y < 30; x += 10, y+= 10) {
            Token token = new Token(x, y);
            token.setVisible(false);
            minigame.getChildren().add(token);
        }

        StartingCircle startingCircle = new StartingCircle();
        minigame.getChildren().add(startingCircle);
        startingCircle.setTranslateX(-30);
        startingCircle.setTranslateY(-30);

    }

    public void cleanup() {
        for (Node node : minigame.getChildren()) {
            if (node instanceof Token) {
                node.setVisible(false);
            }
        }
    }

    private class Token extends Circle {

        public Token(double x, double y) {
            super();

            this.setRadius(5);
            this.setFill(Color.YELLOW);
            this.setStroke(Color.BLACK);

            this.setTranslateX(x);
            this.setTranslateY(y);

            this.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, event -> {
                if (this.isVisible() &&
                        viewHandler.getState().getCurrentState() == GameStates.MINIGAME_PLAY) {

                    this.setVisible(false);
                    tokenCount++;
                    score += 10;

                    if (tokenCount == tokenTotal) {

                        timer.stop();
                        cleanup();

                        // vic roy
                        viewHandler.getState().getPlayerController().
                                getCurrentMinigamePlayer().setMinigameScore(score);

                        viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);
                    }
                }
            });
        }

    }

    private class StartingCircle extends Circle {
        public StartingCircle() {
            super();
            this.setRadius(5);
            this.setFill(Color.RED);
            this.setStroke(Color.BLACK);

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                if (this.isVisible()) {

                    this.setVisible(false);

                    for (Node node : minigame.getChildren()) {
                        if (node instanceof Token) {
                            node.setVisible(true);
                        }
                    }

                    timer.play();
                }

            });
        }
    }

}
