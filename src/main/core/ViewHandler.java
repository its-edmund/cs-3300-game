package core;

import state.State;

import java.io.IOException;

public interface ViewHandler {

    void launchStartWindow() throws IOException;
    void launchTeamSelectionMenu() throws IOException;
    void launchGameboard() throws IOException;
    void updateState(State state);
    State getState();
}
