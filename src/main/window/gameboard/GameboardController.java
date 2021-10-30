package window.gameboard;

import core.AbstractController;
import core.AppViewHandler;
import core.Resizable;
import core.ViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import tile.Tile;
import tile.Wall;
import tile.WallOrientationEnum;
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

        rollDice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dice.rollDice();
                diceLabel.setText("Dice Roll: " + dice.getValue());
                gameStateController.handleDiceRoll(dice.getValue());
            }
        });

        if (viewHandler != null) {
            ChangeListener<Number> stageWidthListener = (observable, oldVal, newVal) -> {
                for (Node node : board.getChildren()) {
                    if (node instanceof Resizable) {
                        ((Resizable) node).onResize();
                    }
                }
            };
            ChangeListener<Number> stageHeightListener = (observable, oldVal, newVal) -> {
                for (Node node : board.getChildren()) {
                    if (node instanceof Resizable) {
                        ((Resizable) node).onResize();
                    }
                }
            };
            viewHandler.addEventOnScreenWidthChange(stageWidthListener);
            viewHandler.addEventOnScreenHeightChange(stageHeightListener);
        }

        // It would be nice if we could get this to work...
//        viewHandler.triggerResize();
    }

    private void createBoard() {
        int BOARD_SIZE = 15;

        path = new ArrayList<>();

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

            Tile tile = new Tile(x, y, this);

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

            Tile tile = new Tile(x, y, this);

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

            Tile tile = new Tile(x, y, this);

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

            Tile tile = new Tile(x, y, this);

            if ((path.size() % 2) == 0) {
                tile.setType(LOSE_MONEY);
            } else {
                tile.setType(GAIN_MONEY);
            }

            board.getChildren().addAll(tile);
            path.add(tile);

            x -= step;
        }

        // Add the special tiles to the board
        path.get(0).setType(START);
        path.get(11).setType(CHANCE);
        path.get(19).setType(CHANCE);
        path.get(26).setType(CHANCE);
        path.get(34).setType(CHANCE);
        path.get(41).setType(CHANCE);
        path.get(49).setType(CHANCE);

        path.get(7).addWall(WallOrientationEnum.TOP);

        // Add all player tokens to the first square
        PlayerController playerController = viewHandler.getState().getPlayerController();

        for (Player player : playerController.getPlayers()) {
            path.get(0).addToken(player.getToken());
        }

    }

    private void createPlayerProfiles() {
        // Get the gamestate
        PlayerController playerController = viewHandler.getState().getPlayerController();

        for (Player player : playerController.getPlayers()) {

            PlayerProfile playerProfile = new PlayerProfile(player);
            playerProfileHbox.getChildren().addAll(playerProfile);
        }

        changePlayerStatus(0);
    }

    private void createChanceCards() {
        chanceCard = new ChanceCard(viewHandler);
        board.getChildren().add(chanceCard);
    }

    // Board functions
    public Pane getBoard() {
        return board;
    }
    public void repositionChild(double x, double y, Node child) {
//        double screenWidth = (viewHandler.getScreenDimensions()[0] - 16);
//        double screenHeight = (viewHandler.getScreenDimensions()[1] - 40 - 85);

        double screenWidth = (viewHandler.getScreenDimensions()[0] - 16);
        double screenHeight =
                (viewHandler.getScreenDimensions()[1] - 40 - hudVBox.getHeight());

        // 16: width offscreen
        double newX = x * screenHeight + (screenWidth - screenHeight) / 2;
        // 40: height offscreen
        // 85: height of HUD
        double newY = y * screenHeight;

        child.relocate(newX, newY);
    }
    public ViewHandler getViewHandler() {
        return viewHandler;
    }

    // Return 0 if movement succeeded
    // Return positive number if movement blocked by wall
    public int moveToken(Token playerToken, int moveAmount) {
        path.get(playerToken.getTokenLocation()).removeToken(playerToken);

        int newLoc = playerToken.getTokenLocation() + moveAmount;

        // Check if the playerToken is at the end
        if (path.size() - 1 < newLoc || playerToken.getFinished()) {
            playerToken.setFinished(true);
            newLoc = 0;
        }

        // Check for a wall
        // If the move amount is negative, we won't check for a wall
        for (int i = 0; i <= moveAmount; i++) {
            Tile tile = path.get(playerToken.getTokenLocation() + i);

            if (tile.hasWall() && tile.getWall().isActive()) {
                playerToken.setTokenLocation(playerToken.getTokenLocation() + i - 1);
                path.get(playerToken.getTokenLocation()).addToken(playerToken);
                return moveAmount - i + 1;
            }
        }

        // If there is no wall, move to the location
        playerToken.setTokenLocation(newLoc);
        path.get(playerToken.getTokenLocation()).addToken(playerToken);
        return 0;
    }

    public Tile getTile(int tileLocation) {
        return path.get(tileLocation);
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
