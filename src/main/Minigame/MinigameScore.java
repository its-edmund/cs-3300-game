package Minigame;

import core.ViewHandler;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MinigameScore {

    Text scoreText;

    DoubleProperty score;

    ViewHandler viewHandler;

    public MinigameScore(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        score = new SimpleDoubleProperty();

        setupScoreText();
    }

    private void setupScoreText() {
        scoreText = new Text();

        scoreText.setStroke(Color.BLACK);

        scoreText.textProperty().bind(Bindings.createStringBinding(
                () -> ("Score: " + Math.floor(score.get())), score)
        );

        AbstractMinigameController minigame =
                viewHandler.getState().getCurrentMinigame();

        scoreText.setTranslateY(minigame.HEIGHT - 20);
        viewHandler.getState().getCurrentMinigame().getChildren().add(scoreText);
    }

    public double getScore() {
        return score.get();
    }

    public void setScore(double score) {
        this.score.set(score);
    }

    public void cleanup() {
        viewHandler.getState().getCurrentMinigame().getChildren().remove(scoreText);
    }


}
