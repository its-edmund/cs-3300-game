package Minigame;

import core.GameStates;
import core.TestViewHandler;
import core.ViewHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class TheCircleMinigame extends AbstractMinigame{

    private ViewHandler viewHandler;
    private MinigameController minigame;
    MinigameTimer timer;
    MinigameScore score;

    double currentTime = 5.0;

//    public TheCircleMinigame(ViewHandler viewHandler, MinigameController minigame) {
//        this.viewHandler = viewHandler;
//        this.minigame = minigame;
//
//        pattern1();
//    }
    public TheCircleMinigame() {
        this.viewHandler = null;
        this.minigame = null;
    }

    public TheCircleMinigame(ViewHandler viewHandler, MinigameController minigame) {
        this.viewHandler = viewHandler;
        this.minigame = minigame;

        selectedMinigame = 1;
    }

    public void pattern1() {

        timer = new MinigameTimer(viewHandler, () -> {
            endMinigameRound();
        });
        timer.setTime(5.0);

        score = new MinigameScore(viewHandler);

        timer.setTimerUpdatedEvent(() -> {
            updateScore();
        });

        ArrayList<int[]> pattern = new ArrayList<>();
        pattern.add(new int[]{50, 0});
        pattern.add(new int[]{-50,0});
        pattern.add(new int[]{30, 40});
        pattern.add(new int[]{-30,-40});
        pattern.add(new int[]{0, 50});
        pattern.add(new int[]{0,-50});

        new MinigameCircleController(pattern);

 }

    public void cleanup() {
        for (Node node : minigame.getChildren()) {
            if (node instanceof MinigameCircle) {
                node.setVisible(false);
            }
        }

        timer.cleanup();
        score.cleanup();
    }

    public void endMinigameRound() {
        cleanup();

        double timeLeft = Math.max(timer.getTime(), 0);

        viewHandler.getState().getPlayerController().
                getCurrentMinigamePlayer().setMinigameScore((int)(timeLeft * 100));
        viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);
    }

    public TestViewHandler getViewHandler() {
        currentTime = 60.0;
        return new TestViewHandler(0, currentTime);
    }
    public void testEndGame(TestViewHandler vh) {
        vh.setState(1);
    }

    @Override
    public String getMinigameTitle() {
        return "The Circle Minigame";
    }

    @Override
    public String getMinigameDescription() {
        return "In this minigame, you must collect all the circles within the time period. To collect a circle,"
                + "simply hover over it with your mouse. The player that collects ALL of the circles in the shortest amount of time wins!"
                + " The timer begins upon touching the first circle.";
    }

    @Override
    public void playMinigame() {
        switch (selectedMinigame) {
            case 1:
                pattern1();
                break;
        }
    }

    private void updateScore() {
        score.setScore(Math.floor(timer.getTime() * 100));
    }

    private class MinigameCircleController {

        ArrayList<MinigameCircle> circles;

        public MinigameCircleController(ArrayList<int[]> coordList) {
            initializeCircles(coordList.size());

            circles.get(0).setFirst();
            circles.get(circles.size() - 1).setLast();

            setCirclePositions(coordList);
            setNextCircles();
            addCirclesToBoard();
        }

        private void initializeCircles(int numCircles) {

            circles = new ArrayList<>();

            for (int i = 0; i < numCircles; i++) {
                circles.add(new MinigameCircle());
            }

        }

        private void setCirclePositions(ArrayList<int[]> coordList) {

            for (int i = 0; i < circles.size(); i++) {
                int[] coords = coordList.get(i);
                circles.get(i).setTranslateX(coords[0]);
                circles.get(i).setTranslateY(coords[1]);
            }

        }

        private void setNextCircles() {

            for (int i = 0; i < circles.size(); i++) {
                MinigameCircle curr = circles.get(i);

                MinigameCircle next = null;

                if (i != circles.size() - 1) {
                    next = circles.get(i + 1);
                }

                curr.setNextCircle(next);
            }

        }

        private void addCirclesToBoard() {

            for (MinigameCircle circle : circles) {
                minigame.getChildren().addAll(circle);
            }

            for (int i = 0; i < circles.size(); i++) {

                MinigameCircle circle = circles.get(i);

                if (circle.isFirst) {
                    circle.setPresent();

                    if (circle.next != null) {
                        circle.next.setSilhouette();
                        i++;
                    }
                } else {
                    circle.setInvisible();
                }
            }

        }

    }

    private class MinigameCircle extends Circle {

        private MinigameCircle next;
        private boolean isFirst;
        private boolean isLast;

        public MinigameCircle() {
            this.setRadius(15);
        }

        public void setNextCircle(MinigameCircle nextCircle) {
            this.next = nextCircle;

            this.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, event -> {

                if (isHoverableCircle() &&
                        viewHandler.getState().getCurrentState() == GameStates.MINIGAME_PLAY) {

                    updateCircleVisibilityAfterHover();

                    if (isLast) {
                        timer.stopTimer();

                    } else {
                        // upon hovering over the first circle, start the timer
                        if (isFirst) {
                            // start the timer
                            timer.startTimer();
                        }

                    }
                }
            });
        }

        private void updateCircleVisibilityAfterHover() {
            this.setInvisible();

            if (next != null) {
                next.setPresent();

                if (next.next != null) {
                    next.next.setSilhouette();
                }
            }

        }

        private void setSilhouette() {
            this.setVisible(true);
            this.setStroke(Color.BLACK);
//            this.setFill(Color.BLACK);
            this.setFill(Color.TRANSPARENT);
            this.getStrokeDashArray().addAll(1d, 1d);
        }

        private void setPresent() {
            this.setVisible(true);
            this.setStroke(Color.BLACK);

            if (this.isLast) {
                this.setFill(Color.YELLOW);
            } else {
                this.setFill(Color.ORANGERED);
            }
        }

        private void setInvisible() {
            this.setVisible(false);
        }

        public void setFirst() {
            this.isFirst = true;
        }

        public void setLast() {
            this.isLast = true;
        }

        private boolean isHoverableCircle() {
            return this.isVisible() &&
                    (this.getFill().equals(Color.ORANGERED) || this.getFill().equals(Color.YELLOW));
        }
    }

}
