import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Stage stage;
    private BorderPane root;
    private Scene gameScene;
    private HBox buttons;

    private Button move1Button;
    private Button move3Button;


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setup
        try {
            move1Button = new Button("Move 1");
            move3Button = new Button("Move 3");
            this.stage = primaryStage;
            root = new BorderPane();
            this.gameScene = new Scene(root, WIDTH, HEIGHT);
            buttons = new HBox();
            move1Button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    return;
                    // Move Player
                }
            });
            move3Button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    return;
                }
            });
            this.buttons.getChildren().addAll(move1Button, move3Button);
            root.setTop(buttons);
            this.stage.setScene(gameScene);

            primaryStage.show();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void update(float dt) {
        return;
    }
}
