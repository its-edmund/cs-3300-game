package window.gameboard;

import core.AbstractController;
import core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tile.Tile;
import window.player.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameboardController extends AbstractController {

    @FXML private Button move1Button;
    @FXML private Button move3Button;
    @FXML private GridPane board;

    public ArrayList<ArrayList<Integer>> path;
    public Player player;

    public GameboardController(ViewHandler viewHandler) {
        super(viewHandler);
        player = new Player();
        board = new GridPane();
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        move1Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                move1Forward();
            }
        });
        move3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                move3Forward();
            }
        });
        createBoard();
    }

    private void move1Forward() {
        board.getChildren().removeAll(this.player);
        this.player.setCurrentLocation(this.player.getCurrentLocation() + 1);
        board.add(this.player, path.get(this.player.getCurrentLocation()).get(0), path.get(this.player.getCurrentLocation()).get(1));
    }
    private void move3Forward() {
        board.getChildren().removeAll(this.player);
        this.player.setCurrentLocation(this.player.getCurrentLocation() + 3);
        board.add(this.player, path.get(this.player.getCurrentLocation()).get(0), path.get(this.player.getCurrentLocation()).get(1));
    }
    private void createBoard() {
        int BOARD_SIZE = 15;

        path = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            ArrayList<Integer> coordinate = new ArrayList<Integer>();
            coordinate.add(0);
            coordinate.add(i);

            path.add(coordinate);

            Tile tile = new Tile(0, i);
            tile.setFill(Color.BURLYWOOD);
            tile.setStroke(Color.BLACK);

            Text text = new Text();
            if (i == 0) {
                tile.setFill(Color.BLUE);
                text.setText("Start");
            }
            text.setFont(Font.font(12));
            board.add(new StackPane(tile, text), 0, i);
        }

        for (int i = 1; i < BOARD_SIZE; i++) {
            ArrayList<Integer> coordinate = new ArrayList<Integer>();
            coordinate.add(i);
            coordinate.add(14);

            path.add(coordinate);

            Tile tile = new Tile(i, 14);
            tile.setFill(Color.BURLYWOOD);
            tile.setStroke(Color.BLACK);

            Text text = new Text();
            text.setFont(Font.font(40));
            board.add(new StackPane(tile, text), i, 14);
        }

        for (int i = BOARD_SIZE - 2; i >= 0; i--) {
            ArrayList<Integer> coordinate = new ArrayList<Integer>();
            coordinate.add(14);
            coordinate.add(i);

            path.add(coordinate);

            Tile tile = new Tile(14, i);
            tile.setFill(Color.BURLYWOOD);
            tile.setStroke(Color.BLACK);

            Text text = new Text();
            text.setFont(Font.font(40));
            board.add(new StackPane(tile, text), 14, i);
        }

        for (int i = BOARD_SIZE - 2; i > 0; i--) {
            ArrayList<Integer> coordinate = new ArrayList<Integer>();
            coordinate.add(i);
            coordinate.add(0);

            path.add(coordinate);

            Tile tile = new Tile(i, 0);
            tile.setFill(Color.BURLYWOOD);
            tile.setStroke(Color.BLACK);

            Text text = new Text();
            if (i == 1) {
                tile.setFill(Color.GREEN);
                text.setText("End");
            }
            text.setFont(Font.font(12));
            board.add(new StackPane(tile, text), i, 0);
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i == 0 || i == BOARD_SIZE - 1 || j == 0 || j == BOARD_SIZE - 1) {

                    if (i == 0 && j == 0) {
                        this.player = new Player();

                        this.player.toFront();
                        board.add(this.player, path.get(0).get(0), path.get(0).get(1));
                    }

                    // GridPane.setRowIndex(tile, i);
                    // GridPane.setColumnIndex(tile, j);
                    // gameBoard.getChildren().addAll(tile, text);
                    // tile.setOnMouseClicked(event -> tile.setFill(Color.RED));
                }
            }
        }
        this.player.setLocationLimit(path.size());
    }
}
