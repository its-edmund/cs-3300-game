package fxmlexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tile.Tile;
import tile.TileType;

// Tutorial: https://docs.oracle.com/javafx/2/get_started/fxml_tutorial.htm

public class FXMLExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public Parent createBoard() {
       int BOARD_SIZE = 15;

        GridPane gameBoard = new GridPane();
        gameBoard.setPrefSize(755, 755);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i == 0 || i == BOARD_SIZE - 1 || j == 0 || j == BOARD_SIZE - 1) {
                    Tile tile;

                    if (i == 0 && j == 0) {
                        tile = new Tile(TileType.START, i, j);
                    } else {
                        tile = new Tile(TileType.STANDARD, i, j);
                    }

                    Text text = new Text();
                    text.setFont(Font.font(40));
                    gameBoard.add(new StackPane(tile, text), j, i);

                    // GridPane.setRowIndex(tile, i);
                    // GridPane.setColumnIndex(tile, j);
                    // gameBoard.getChildren().addAll(tile, text);
                    // tile.setOnMouseClicked(event -> tile.setFill(Color.RED));
                }
            }
        }
        return gameBoard;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent board = createBoard();

        Scene scene = new Scene(board, 755, 755);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
