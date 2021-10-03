package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import core.AbstractWindow;
import core.ViewHandler;
import core.AppPaths;

import window.ScreenEnum;
import window.WindowFactory;

public class AppViewHandler implements ViewHandler {

    private final Stage primaryStage;
    private final ResourceBundle bundle;
    private final WindowFactory windowFactory;

    public AppViewHandler(Stage primaryStage, ResourceBundle bundle) {
        this.primaryStage = primaryStage;
        this.bundle = bundle;
        this.windowFactory = new WindowFactory();
    }

    @Override
    public void launchStartWindow() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.START, this, bundle));
    }

    @Override
    public void launchTeamSelectionMenu() throws IOException {
//        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.TEAM_SELECTION, this, bundle));
    }

    @Override
    public void launchGameboard() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.GAMEBOARD, this, bundle));
    }


    private void buildAndShowScene(Stage stage, AbstractWindow window) throws IOException {

        // Alternatively, use:
        //        stage.getIcons().add(new Image(
//                window.iconFilePath()
//        ));

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(window.iconFilePath())) {
            stage.getIcons().add(new Image(is));
        }

        stage.setTitle(bundle.getString(window.titleBundleKey()));
        stage.setResizable(window.resizable());
        stage.setScene(new Scene(window.root()));
        stage.show();
    }
}
