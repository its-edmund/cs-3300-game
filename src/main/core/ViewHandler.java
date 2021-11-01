package core;

import javafx.beans.value.ChangeListener;
import state.State;

import java.io.IOException;

public interface ViewHandler {

    void launchStartWindow() throws IOException;
    void launchTeamSelectionMenu() throws IOException;
    void launchGameboard() throws IOException;

    void launchVictoryScreen() throws IOException;

    void updateState(State state);

    double[] getScreenDimensions();
    void addEventOnScreenWidthChange(ChangeListener<Number> event);
    void addEventOnScreenHeightChange(ChangeListener<Number> event);
    State getState();
    void triggerResize();
}
