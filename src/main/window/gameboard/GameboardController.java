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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import tile.Tile;
import token.Token;
import window.dice.Dice;
import window.player.Player;
import window.player.PlayerController;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static tile.TileType.*;

public class GameboardController extends AbstractController {

    @FXML private Button move1Button;
    @FXML private Button move3Button;
    @FXML private Button rollDice;
    @FXML private Pane board;
    @FXML private Label diceLabel;

    @FXML private HBox playerProfileHbox;

    @FXML private VBox hudVBox;

    GameStateController gameStateController;
    ChanceCard chanceCard;
    Dice dice = new Dice(6);

    public ArrayList<Tile> path;

    public GameboardController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {

        createBoard();
        createPlayerProfiles();
        createChanceCards();

        gameStateController = new GameStateController(viewHandler, this);

        for (Player player : viewHandler.getState().getPlayerController().getPlayers()) {
            player.setupPlayerMover(this);
        }


        move1Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                gameStateController.handleDiceRoll(1);
            }
        });
        move3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStateController.handleDiceRoll(3);
            }
        });
        rollDice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dice.rollDice();
                diceLabel.setText("Dice Roll: " + dice.getValue());
                gameStateController.handleDiceRoll(dice.getValue());
            }
        });

        viewHandler.triggerResize();
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

        path = new ArrayList<>();

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
        double x = 0.5;
        double y = 0;
        double step = (0.4 / 15);
        
        while (x >= 0.1) {

            y = -1 * x + 0.6;

            Tile tile = new Tile(x, y, viewHandler);

            if ((path.size() % 2) == 0) {
                tile.setType(LOSE_MONEY);
            } else {
                tile.setType(GAIN_MONEY);
            }

            board.getChildren().addAll(tile);
            path.add(tile);

            x -= step;
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

            Tile tile = new Tile(x, y, viewHandler);

            if ((path.size() % 2) == 0) {
                tile.setType(LOSE_MONEY);
            } else {
                tile.setType(GAIN_MONEY);
            }

            board.getChildren().addAll(tile);
            path.add(tile);

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

        while (x < 0.9 - step) {

            y = -1 * x + 1.4;

            Tile tile = new Tile(x, y, viewHandler);

            if ((path.size() % 2) == 0) {
                tile.setType(LOSE_MONEY);
            } else {
                tile.setType(GAIN_MONEY);
            }

            board.getChildren().addAll(tile);
            path.add(tile);

            x += step;
        }
        
        /*
            *
          |    *
        |        *
          |    |
            |
         */
        x = 0.9;
        y = 0;

        while (x >= 0.5 + step) {

            y = x - 0.4;

            Tile tile = new Tile(x, y, viewHandler);

            if ((path.size() % 2) == 0) {
                tile.setType(LOSE_MONEY);
            } else {
                tile.setType(GAIN_MONEY);
            }

            board.getChildren().addAll(tile);
            path.add(tile);

            x -= step;
        }

        // Add all player tokens to the first square
        PlayerController playerController = viewHandler.getState().getPlayerController();

//        path.get(0).getRectangle().setFill(Color.GREEN);
//        path.get(15).getRectangle().setFill(Color.BLUE);
//        path.get(30).getRectangle().setFill(Color.BLUE);
//        path.get(45).getRectangle().setFill(Color.BLUE);

        path.get(0).setType(START);
        path.get(11).setType(CHANCE);
        path.get(19).setType(CHANCE);
        path.get(26).setType(CHANCE);
        path.get(34).setType(CHANCE);
        path.get(41).setType(CHANCE);
        path.get(49).setType(CHANCE);

        for (Player player : playerController.getPlayers()) {
            path.get(0).addToken(player.getToken());
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

//            StackPane stackPane = new StackPane();
//
//            Rectangle playerProfileRectangle = new Rectangle();
//            playerProfileRectangle.setWidth(200);
//            playerProfileRectangle.setHeight(50);
//            playerProfileRectangle.setFill(player.color);
//            playerProfileRectangle.setStroke(Color.BLACK);
//            playerProfileRectangle.setStrokeWidth(5);
//
//            Text playerProfileText = new Text();
//            playerProfileText.setText(player.name + "\n" + "$" + player.money);
//
//            stackPane.getChildren().addAll(playerProfileRectangle, playerProfileText);

            PlayerProfile playerProfile = new PlayerProfile(player);
            playerProfileHbox.getChildren().addAll(playerProfile);
        }

        changePlayerStatus(0);
    }

    private void createChanceCards() {
        chanceCard = new ChanceCard(viewHandler);
        board.getChildren().add(chanceCard);
    }

    public void moveToken(Token playerToken, int moveAmount) {
        path.get(playerToken.getTokenLocation()).removeToken(playerToken);

        int newLoc = playerToken.getTokenLocation() + moveAmount;

        if (path.size() - 1 < newLoc || playerToken.getFinished()) {
            playerToken.setFinished(true);
            newLoc = 0;
        }

        playerToken.setTokenLocation(newLoc);

        path.get(playerToken.getTokenLocation()).addToken(playerToken);
    }

    public void changePlayerStatus(int newPlayerTurnIndex) {
        PlayerProfile prevPlayerStackPane;
        PlayerProfile currPlayerStackPane;

        if (newPlayerTurnIndex < 0) {
            throw new IllegalArgumentException();
        }

        if (newPlayerTurnIndex == 0) {
            prevPlayerStackPane =
                    (PlayerProfile) playerProfileHbox.getChildren().get(playerProfileHbox.getChildren().size() - 1);
            currPlayerStackPane =
                    (PlayerProfile) playerProfileHbox.getChildren().get(0);
        } else {
            prevPlayerStackPane =
                    (PlayerProfile) playerProfileHbox.getChildren().get(newPlayerTurnIndex - 1);
            currPlayerStackPane =
                    (PlayerProfile) playerProfileHbox.getChildren().get(newPlayerTurnIndex);
        }

        prevPlayerStackPane.getPlayerProfileRectangle().setStroke(Color.BLACK);
        currPlayerStackPane.getPlayerProfileRectangle().setStroke(Color.GREEN);
    }

    public Tile getTileTokenOccupies(Token playerToken) {
        return path.get(playerToken.getTokenLocation());
    }
}
