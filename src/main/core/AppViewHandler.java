package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.tools.javac.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import core.AbstractWindow;
import core.ViewHandler;
import core.AppPaths;

import javafx.stage.WindowEvent;
import javafx.util.Duration;
import state.State;
import window.ScreenEnum;
import window.WindowFactory;

public class AppViewHandler implements ViewHandler {

    public static Stage primaryStage;
    private final ResourceBundle bundle;
    private final WindowFactory windowFactory;

    private State state;

    private static ArrayList<Resizable> newNodes;

    public static final double INITIAL_SCREEN_WIDTH = 600;
    public static final double INITIAL_SCREEN_HEIGHT = 400;

    public AppViewHandler(Stage primaryStage, ResourceBundle bundle) {
        this.primaryStage = primaryStage;
        this.bundle = bundle;
        this.windowFactory = new WindowFactory();
        this.state = new State();

        newNodes = new ArrayList<>();

        Timeline resizeNewNodes = new Timeline(new KeyFrame(Duration.seconds(0.1),
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    resizeNewNodes();
                }
            })
        );

        resizeNewNodes.setCycleCount(Timeline.INDEFINITE);
        resizeNewNodes.play();

    }

    @Override public void launchStartWindow() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.START, this, bundle));
    }
    @Override public void launchTeamSelectionMenu() throws IOException {
       buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.CONFIG, this, bundle));
    }
    @Override public void launchGameboard() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.GAMEBOARD, this, bundle));
    }
    @Override public void launchVictoryScreen() throws IOException {
        buildAndShowScene(primaryStage, windowFactory.createWindow(ScreenEnum.VICTORY, this, bundle));
    }

    @Override
    public void updateState(State state) {
        this.state = state;
    }

    // Screen Size Methods
    @Override
    public double[] getScreenDimensions() {
        if (primaryStage != null) {
            double[] dimensions = {primaryStage.getWidth(), primaryStage.getHeight()};
            return dimensions;
        } else {
            return new double[]{0,0};
        }

    }
    public static double getScreenWidth() {
        if (primaryStage != null) {
            return primaryStage.getWidth();
        } else {
            return -1;
        }
    }
    public static double getScreenHeight() {
        if (primaryStage != null) {
            return primaryStage.getHeight();
        } else {
            return -1;
        }
    }

    // Resizing Methods

    public static void addNewNodeToResize(Resizable node) {
        newNodes.add(node);
    }

    public static void resizeNewNodes() {
        if (!newNodes.isEmpty()) {
            for (Resizable node : newNodes) {
                if (!node.justAddedToScene()) {
                    node.onResize();
                }
            }

            // empty the newNodes arrayList
            newNodes = new ArrayList<>();
        }
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

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(window.iconFilePath())) {
            stage.getIcons().add(new Image(is));
        }

        stage.setTitle(bundle.getString(window.titleBundleKey()));
        stage.setResizable(window.resizable());
        stage.setScene(new Scene(window.root()));
        stage.show();
    }

}
