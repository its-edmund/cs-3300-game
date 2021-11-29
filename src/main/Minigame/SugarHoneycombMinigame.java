package Minigame;

import core.GameStates;
import core.ViewHandler;
import core.TestViewHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class SugarHoneycombMinigame extends AbstractMinigame{

    private ViewHandler viewHandler;
    private MinigameController minigameController;
    private MinigameTimer timer;
    MinigameScore score;
    MinigameTokenController minigameTokenController;

    private double currentTime;

    private int tokenCount = 0;
    private int tokenTotal;

//    public SugarHoneycombMinigame(ViewHandler viewHandler, MinigameController minigame) {
//        this.viewHandler = viewHandler;
//        this.minigame = minigame;
//
//        currentTime = 3.0;
//
//        shape1();
//    }
    public SugarHoneycombMinigame() {
        this.viewHandler = null;
        this.minigameController = null;
        currentTime = 0.0;
    }

    public SugarHoneycombMinigame(ViewHandler viewHandler, MinigameController minigameController) {
        this.viewHandler = viewHandler;
        this.minigameController = minigameController;

        selectedMinigame = 1;
    }

    public void shape1() {

        timer = new MinigameTimer(viewHandler, this::endMinigameRound);
        timer.setTime(3.0);

        score = new MinigameScore(viewHandler);

        ArrayList<int[]> pattern = new ArrayList<>();

        nShape(pattern, 75, 0);
        nShape(pattern, 0, 0);
        nShape(pattern, -75, 0);

        int[] startingCoords = new int[] {-30, -30};

        minigameTokenController = new MinigameTokenController(pattern, startingCoords);

    }

    private void nShape(ArrayList<int[]> pattern, int centerX, int centerY) {
        for (int x = -30, y = -30; y < 30; y+=10) {
            pattern.add(new int[]{centerX + x, centerY + y});
        }
        for (int x = 30, y = -30; y < 30; y+=10) {
            pattern.add(new int[]{centerX + x, centerY + y});
        }
        for (int x = -30, y = -30; x < 30 && y < 30; x += 10, y+= 10) {
            pattern.add(new int[] {centerX + x, centerY + y});
        }
    }

    public void endMinigameRound() {

        viewHandler.getState().getPlayerController().
                getCurrentMinigamePlayer().setMinigameScore((int)score.getScore());

        viewHandler.getState().updateState(GameStates.MINIGAME_INDIVIDUAL_SCORE);

        cleanup();
    }

    public void cleanup() {

        minigameTokenController.removeTokensFromBoard();

        timer.cleanup();
        score.cleanup();
    }

    private void updateScore() {
        minigameTokenController.incrementNumTokensCollected();
        score.setScore(minigameTokenController.getNumTokensCollected() * 10);
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
        return "Sugar Honeycomb Minigame";
    }

    @Override
    public String getMinigameDescription() {
        return "In this minigame, your goal is to collect as many yellow tokens as possible in the given time period. However, you " +
                "must avoid touching the black outline! If you do so, your score will be halved! " +
                "The player that collects the most tokens wins!";
    }

    @Override
    public void playMinigame() {
        switch (selectedMinigame) {
            case 1:
                shape1();
                break;
        }
    }


    private class MinigameTokenController {
        ArrayList<Token> tokens;
        StartingCircle startingCircle;
        int numTokensCollected;

        public MinigameTokenController(ArrayList<int[]> coordList, int[] startingCoords) {

            numTokensCollected = 0;

            initializeTokens(coordList.size());
            setCirclePositions(coordList, startingCoords);
            addTokensToBoard();
        }

        private void initializeTokens(int numTokens) {
            tokens = new ArrayList<>();

            for (int i = 0; i < numTokens; i++) {
                tokens.add(new Token());
            }

            startingCircle = new StartingCircle();
        }

        private void setCirclePositions(ArrayList<int[]> coordList, int[] startingCoords) {
            for (int i = 0; i < coordList.size(); i++) {
                int[] coords = coordList.get(i);
                tokens.get(i).setTranslateX(coords[0]);
                tokens.get(i).setTranslateY(coords[1]);
            }

            startingCircle.setTranslateX(startingCoords[0]);
            startingCircle.setTranslateY(startingCoords[1]);
        }

        private void addTokensToBoard() {
            minigameController.getChildren().addAll(tokens);
            minigameController.getChildren().add(startingCircle);

            setTokenActivity(false);
        }

        public void removeTokensFromBoard() {

            numTokensCollected = 0;

            minigameController.getChildren().removeAll(tokens);
            minigameController.getChildren().remove(startingCircle);
        }

        private void setTokenActivity(boolean activity) {
            for (Token token : tokens) {
                token.setActive(activity);
            }
        }

        public int getNumTokensCollected() {
            return numTokensCollected;
        }

        public void incrementNumTokensCollected() {
            if (numTokensCollected < tokens.size())
                numTokensCollected++;
        }

        public boolean collectedAllTokens() {
            return numTokensCollected == tokens.size();
        }

    }

    private class Token extends CollectableCircle {

        private boolean isCollected;

        public Token() {
            super();

            this.setRadius(5);
            this.setFill(Color.YELLOW);
            this.setStroke(Color.BLACK);

            setTranslateX(0);
            setTranslateY(0);

            setActive(false);
            setCollected(false);

            this.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, event -> {
                if (!isCollected && isActive() &&
                        viewHandler.getState().getCurrentState() == GameStates.MINIGAME_PLAY) {

                    setCollected(true);
                    setActive(false);

                    updateScore();

                    if (minigameTokenController.collectedAllTokens()) {
                        timer.stopTimer();
                    }
                }
            });
        }


        @Override
        public boolean isActive() {
            return isVisible();
        }

        @Override
        public void setActive(boolean active) {
            setVisible(active);
        }

        @Override
        public boolean isCollected() {
            return isCollected;
        }

        @Override
        public void setCollected(boolean collected) {
            isCollected = collected;
        }
    }

    private class StartingCircle extends CollectableCircle {

        private boolean isCollected;

        public StartingCircle() {
            super();
            this.setRadius(5);
            this.setFill(Color.RED);
            this.setStroke(Color.BLACK);

            setCollected(false);
            setActive(true);

            this.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                if (isActive() && !isCollected()) {

                    setCollected(true);
                    setActive(false);

                    minigameTokenController.setTokenActivity(true);

                    timer.startTimer();
                }

            });
        }
    }

}
