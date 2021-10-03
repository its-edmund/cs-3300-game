package core;

import java.io.IOException;

public interface ViewHandler {

    void launchStartWindow() throws IOException;
    void launchTeamSelectionMenu() throws IOException;
    void launchGameboard() throws IOException;
}
