package window.start;

import core.AbstractController;
import core.GameMusic;
import core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController extends AbstractController {

    @FXML private Button startButton;

    public StartController(ViewHandler viewHandler) {
        super(viewHandler);

        GameMusic gameMusic = viewHandler.getState().getMusicPlayer();
        gameMusic.playBackgroundMusic();
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        startButton.setOnAction(
                e -> {
                    try {
                        viewHandler.launchTeamSelectionMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        );
    }
}
