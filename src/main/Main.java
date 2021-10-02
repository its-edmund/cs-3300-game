import core.AppPaths;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new AppViewHandler(stage, ResourceBundle.getBundle(AppPaths.RESOURCE_BUNDLE, Locale.getDefault()))
                .launchStartWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
