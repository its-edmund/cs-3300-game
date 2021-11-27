package Minigame;

import core.ViewHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MinigameTimer {

    Timeline timer;
    Text timerText;

    DoubleProperty currentTime;

    TimerEvent timerExpiredEvent;
    TimerEvent timerUpdatedEvent;

    ViewHandler viewHandler;

    public MinigameTimer(ViewHandler viewHandler, TimerEvent timerExpiredEvent) {

        this.timerExpiredEvent = timerExpiredEvent;
        this.viewHandler = viewHandler;
        currentTime = new SimpleDoubleProperty();

        setupTimerText(viewHandler);
        initializeTimeline(timerExpiredEvent);
    }

    private void initializeTimeline(TimerEvent timerExpiredEvent) {

        timer = new Timeline(
                new KeyFrame(Duration.seconds(0.1),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                currentTime.set(currentTime.get() - 0.1);

                                if (timerUpdatedEvent != null)
                                    timerUpdatedEvent.handle();

                                if (currentTime.get() <= 0) {

                                    timer.stop();

                                    timerExpiredEvent.handle();
                                }
                            }
                        }));
        timer.setCycleCount(Animation.INDEFINITE);
    }

    private void setupTimerText(ViewHandler viewHandler) {
        timerText = new Text();

        timerText.setStroke(Color.RED);

        timerText.textProperty().bind(Bindings.createStringBinding(
                () ->
                    ("" + Math.max(Math.floor(currentTime.get() * 100) / 100, 0)), currentTime)
        );


        AbstractMinigameController minigame =
                viewHandler.getState().getCurrentMinigame();

        timerText.setTranslateY(-1 * (minigame.HEIGHT - 20));

        viewHandler.getState().getCurrentMinigame().getChildren().add(timerText);
    }

    public void setTime(double time) {
        currentTime.set(time);
    }

    public void startTimer() {
        timer.play();
    }

    public void stopTimer() {
        timer.stop();
        timerExpiredEvent.handle();
    }

    public double getTime() {
        return currentTime.get();
    }

    public void cleanup() {
        viewHandler.getState().getCurrentMinigame().getChildren().remove(timerText);
    }

    public void setTimerUpdatedEvent(TimerEvent timerUpdatedEvent) {
        this.timerUpdatedEvent = timerUpdatedEvent;
    }

}
