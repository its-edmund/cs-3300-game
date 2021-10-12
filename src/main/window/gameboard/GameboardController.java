package window.gameboard;

import core.AbstractController;
import core.AppViewHandler;
import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tile.Tile;
import window.player.Player;
import window.player.PlayerController;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameboardController extends AbstractController {

    @FXML private Button move1Button;
    @FXML private Button move3Button;
    @FXML private Pane board;

    @FXML private HBox playerProfileHbox;

    @FXML private VBox hudVBox;

    public ArrayList<ArrayList<Integer>> path;
    public Player player;

    public GameboardController(ViewHandler viewHandler) {
        super(viewHandler);
        player = new Player();
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
        createPlayerProfiles();
    }


    private void move1Forward() {
//        board.getChildren().removeAll(this.player);
//        this.player.setCurrentLocation(this.player.getCurrentLocation() + 1);
//        board.add(this.player, path.get(this.player.getCurrentLocation()).get(0), path.get(this.player.getCurrentLocation()).get(1));
    }
    private void move3Forward() {
//        board.getChildren().removeAll(this.player);
//        this.player.setCurrentLocation(this.player.getCurrentLocation() + 3);
//        board.add(this.player, path.get(this.player.getCurrentLocation()).get(0), path.get(this.player.getCurrentLocation()).get(1));
    }

    private void createBoard() {
        int BOARD_SIZE = 15;

        path = new ArrayList<ArrayList<Integer>>();

//        tile.setText("Width: " + viewHandler.getScreenDimensions()[0] + "\n"
//                + "Height: " + viewHandler.getScreenDimensions()[1] + "\n"
//                + "HUD height:" + hudVBox.getHeight());

//        Tile tile1 = new Tile(0, 0);
//        tile1.relocate(0.5, 0.5, viewHandler);
//
//        ChangeListener<Number> stageWidthListener1 = (observable, oldVal, newVal) -> {
////            String labelText = tile.getText();
//////            String newText = "Width: " + viewHandler.getScreenDimensions()[0] + "\n"
//////                    + labelText.substring(labelText.indexOf("Height"), labelText.indexOf("HUD") - 1) + "\n"
//////                    + "HUD height:" + hudVBox.getHeight();
////            tile.setText("Width: " + viewHandler.getScreenDimensions()[0] + "\n"
////                    + "Height: " + viewHandler.getScreenDimensions()[1] + "\n"
////                    + "HUD height:" + hudVBox.getHeight());
//
////            tile.setText(newText);
//
////            tile.setText("Width: " + viewHandler.getScreenDimensions()[0] + "\n"
////                    + "Height: " + viewHandler.getScreenDimensions()[1] + "\n"
////                    + "HUD height:" + hudVBox.getHeight());
//
//
////            tile.relocate(viewHandler.getScreenDimensions()[0],
////                    viewHandler.getScreenDimensions()[1]);
//            tile1.relocate(0.5, 0.5, viewHandler);
//        };
//        ChangeListener<Number> stageHeightListener1 = (observable, oldVal, newVal) -> {
////            String labelText = tile.getText();
////            String newText = labelText.substring(labelText.indexOf("Width"), labelText.indexOf("Height") - 1) + "\n"
////                    + "Height: " + viewHandler.getScreenDimensions()[1] + "\n"
////                    + "HUD height:" + hudVBox.getHeight();
//
////            tile.setText("Width: " + viewHandler.getScreenDimensions()[0] + "\n"
////                    + "Height: " + viewHandler.getScreenDimensions()[1] + "\n"
////                    + "HUD height:" + hudVBox.getHeight());
////
////            tile.setText(newText);
//
////            tile.setText("Width: " + viewHandler.getScreenDimensions()[0] + "\n"
////                    + "Height: " + viewHandler.getScreenDimensions()[1] + "\n"
////                    + "HUD height:" + hudVBox.getHeight());
//
////            tile.relocate(viewHandler.getScreenDimensions()[0],
////                    viewHandler.getScreenDimensions()[1]);
//            tile1.relocate(0.5, 0.5, viewHandler);
//        };
//
//        viewHandler.addEventOnScreenWidthChange(stageWidthListener1);
//        viewHandler.addEventOnScreenHeightChange(stageHeightListener1);
//
//        board.getChildren().addAll(tile1);

       

        /*
            *
          *   |
        *        |
          |    |
            |
         */
        double x = 0.1;
        double y = 0;
        
        while (x < 0.5) {

            y = -1 * x + 0.6;

            Tile tile = new Tile(x, y, (AppViewHandler) viewHandler);
//            tile.relocate(x, y, viewHandler);

            board.getChildren().addAll(tile);

            x += (0.4 / 15);
        }

        
        /*
            |
          |    |
        |        *
          |    *
            *
         */
        x = 0.5;
        y = 0;

        while (x < 0.9) {

            y = -1 * x + 1.4;

            Tile tile = new Tile(x, y, (AppViewHandler) viewHandler);
//            tile.relocate(x, y, viewHandler);

            board.getChildren().addAll(tile);

            x += (0.4 / 15);
        }
        
        /*
            |
          |    |
        *        |
          *    |
            *
         */
        x = 0.1;
        y = 0;

        while (x < 0.5) {

            y = x + 0.4;

            Tile tile = new Tile(x, y, (AppViewHandler) viewHandler);
//            tile.relocate(x, y, viewHandler);

            board.getChildren().addAll(tile);

            x += (0.4 / 15);
        }
        
        /*
            *
          |    *
        |        *
          |    |
            |
         */
        x = 0.5;
        y = 0;

        while (x < 0.9) {

            y = x - 0.4;

            Tile tile = new Tile(x, y, (AppViewHandler) viewHandler);
//            tile.relocate(x, y, viewHandler);

            board.getChildren().addAll(tile);

            x += (0.4 / 15);
        }


        /*
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
         */
    }
    private void createPlayerProfiles() {
        // Get the gamestate
        PlayerController playerController = viewHandler.getState().getPlayerController();

        for (Player player : playerController.getPlayers()) {

            StackPane stackPane = new StackPane();

            Rectangle rectangle = new Rectangle();
            rectangle.setWidth(200);
            rectangle.setHeight(50);
            rectangle.setFill(player.color);
            rectangle.setStroke(Color.BLACK);

            Text text = new Text();
            text.setText(player.name + "\n" + "$" + player.money);

            stackPane.getChildren().addAll(rectangle, text);
            playerProfileHbox.getChildren().addAll(stackPane);
        }
    }
}