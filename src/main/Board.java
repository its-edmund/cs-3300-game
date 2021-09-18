import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board {

    public Parent createBoard() {
        int BOARD_SIZE = 15;

        GridPane gameBoard = new GridPane();
        gameBoard.setPrefSize(755, 755);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i == 0 || i == BOARD_SIZE - 1 || j == 0 || j == BOARD_SIZE - 1) {
                    Rectangle tile = new Rectangle(50, 50);
                    tile.setFill(Color.BURLYWOOD);
                    tile.setStroke(Color.BLACK);

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
}
