package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import core.AbstractWindow;
import core.ViewHandler;
import core.AppPaths;

import state.State;
import window.ScreenEnum;
import window.WindowFactory;

public class AppViewHandler implements ViewHandler {

    private final Stage primaryStage;
    private final ResourceBundle bundle;
    private final WindowFactory windowFactory;

    private State state;

    public final double INITIAL_SCREEN_WIDTH;
    public final double INITIAL_SCREEN_HEIGHT;

    public AppViewHandler(Stage primaryStage, ResourceBundle bundle) {
        this.primaryStage = primaryStage;
        this.bundle = bundle;
        this.windowFactory = new WindowFactory();
        this.state = new State();

        INITIAL_SCREEN_WIDTH = getScreenDimensions()[0];
        INITIAL_SCREEN_HEIGHT = getScreenDimensions()[1];
    }

    @Override
    public void launchStartWindow() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.START, this, bundle));
    }

    @Override
    public void launchTeamSelectionMenu() throws IOException {
       buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.CONFIG, this, bundle));
    }

    @Override
    public void launchGameboard() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.GAMEBOARD, this, bundle));
    }

    @Override
    public void updateState(State state) {
        this.state = state;
    }

    @Override
    public double[] getScreenDimensions() {
        double[] dimensions = {primaryStage.getWidth(), primaryStage.getHeight()};
        return dimensions;
    }

    @Override
    public void addEventOnScreenWidthChange(ChangeListener<Number> event) {
        primaryStage.widthProperty().addListener(event);
    }

    @Override
    public void addEventOnScreenHeightChange(ChangeListener<Number> event) {
        primaryStage.heightProperty().addListener(event);
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void triggerResize() {
        primaryStage.setWidth(INITIAL_SCREEN_WIDTH);
        primaryStage.setHeight(INITIAL_SCREEN_HEIGHT);
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
