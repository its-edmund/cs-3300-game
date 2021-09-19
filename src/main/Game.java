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

    public static final int WIDTH = 755;
    public static final int HEIGHT = 780;

    private Stage stage;
    private BorderPane root;
    private Scene gameScene;

    private Board board = new Board();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setup
        try {

            this.stage = primaryStage;
            this.gameScene = new Scene(board, WIDTH, HEIGHT);
            board.createBoard();
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
