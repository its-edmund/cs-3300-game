package window.gameboard;


import core.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import tile.Tile;
import tile.TileType;
import tile.WallOrientationEnum;
import token.Token;
import window.dice.Dice;
import window.player.Player;
import window.player.PlayerController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

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

    private static Node currentNotification;

    public GameboardController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {

        if (board == null) {
            board = new Pane();
        }

        // add board to the current state
        viewHandler.getState().setGameBoard(board);

        createBoard();
        createPlayerProfiles();
        createChanceCards();

        gameStateController = new GameStateController(viewHandler, this);

        if (viewHandler != null) {
            for (Player player : viewHandler.getState().getPlayerController().getPlayers()) {
                player.setupPlayerMover(this);
            }
        }

        if (rollDice != null) {
            rollDice.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (viewHandler.getState().getCurrentState() == GameStates.GAMEBOARD_IDLE) {
                        dice.rollDice();
                        diceLabel.setText("Dice Roll: " + dice.getValue());

                        Player currentPlayer = viewHandler.getState().getPlayerController().getCurrentPlayer();
                        currentPlayer.getPlayerMover().setRemainingMoves(dice.getValue());
                        viewHandler.getState().updateState(GameStates.MOVING);
                    }

                }
            });
        }

        if (viewHandler != null) {
            ChangeListener<Number> stageWidthListener = (observable, oldVal, newVal) -> {
                for (Node node : board.getChildren()) {
                    if (node instanceof Resizable) {

                        ResizableStackPane resizableNode = (ResizableStackPane) node;
                        resizableNode.onResize();

                        resizableNode.relocate(
                                resizableNode.getPosX(),
                                resizableNode.getPosY()
                        );
                    }
                }
            };
            ChangeListener<Number> stageHeightListener = (observable, oldVal, newVal) -> {
                for (Node node : board.getChildren()) {
                    if (node instanceof Resizable) {

                        ResizableStackPane resizableNode = (ResizableStackPane) node;
                        resizableNode.onResize();

                        resizableNode.relocate(
                                resizableNode.getPosX(),
                                resizableNode.getPosY()
                        );
                    }
                }
            };
            viewHandler.addEventOnScreenWidthChange(stageWidthListener);
            viewHandler.addEventOnScreenHeightChange(stageHeightListener);
        }


        // Link the playerTurnIndex to updating the UI
        PlayerController playerController = viewHandler.getState().getPlayerController();
        playerController.getCurrentPlayerIndexProperty().addListener((obs, oldVal, newVal) -> {
            changePlayerStatus(newVal.intValue());
        });


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
            tile.setPosX(x);
            tile.setPosY(y);

            if ((path.size() % 2) == 0) {
                tile.setType(TileType.LOSE_MONEY);
            } else {
                tile.setType(TileType.GAIN_MONEY);
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
            tile.setPosX(x);
            tile.setPosY(y);

            if ((path.size() % 2) == 0) {
                tile.setType(TileType.LOSE_MONEY);
            } else {
                tile.setType(TileType.GAIN_MONEY);
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
            tile.setPosX(x);
            tile.setPosY(y);

            if ((path.size() % 2) == 0) {
                tile.setType(TileType.LOSE_MONEY);
            } else {
                tile.setType(TileType.GAIN_MONEY);
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
            tile.setPosX(x);
            tile.setPosY(y);

            if ((path.size() % 2) == 0) {
                tile.setType(TileType.LOSE_MONEY);
            } else {
                tile.setType(TileType.GAIN_MONEY);
            }

            board.getChildren().addAll(tile);
            path.add(tile);

            x -= step;
        }

        // Add the special tiles to the board
//        path.get(0).setType(START);
        path.get(0).setType(TileType.END);
        path.get(11).setType(TileType.CHANCE);
        path.get(19).setType(TileType.CHANCE);
        path.get(26).setType(TileType.CHANCE);
        path.get(34).setType(TileType.CHANCE);
        path.get(41).setType(TileType.CHANCE);
        path.get(49).setType(TileType.CHANCE);

        path.get(2).setType(TileType.LAUNCH_MINIGAME);
        path.get(3).setType(TileType.LAUNCH_MINIGAME);
        path.get(4).setType(TileType.LAUNCH_MINIGAME);
        path.get(5).setType(TileType.LAUNCH_MINIGAME);

//        path.get(15).setType(TileType.LAUNCH_MINIGAME);
//        path.get(45).setType(TileType.LAUNCH_MINIGAME);

        path.get(7).addWall(WallOrientationEnum.TOP);
        path.get(30).addWall(WallOrientationEnum.LEFT);
        path.get(42).addWall(WallOrientationEnum.BOTTOM);

        // Add all player tokens to the first square
        PlayerController playerController;
        if (viewHandler != null) {
            playerController = viewHandler.getState().getPlayerController();
        } else {
            playerController = new PlayerController();
        }

        for (Player player : playerController.getPlayers()) {
            path.get(0).addToken(player.getToken());
        }

    }
    private void createPlayerProfiles() {
        // Get the gamestate
        PlayerController playerController;
        if (viewHandler != null) {
            playerController = viewHandler.getState().getPlayerController();
        } else {
            playerController = new PlayerController();
        }

        for (Player player : playerController.getPlayers()) {

            PlayerProfile playerProfile = new PlayerProfile(player);
            playerProfileHbox.getChildren().addAll(playerProfile);
        }

        changePlayerStatus(0);
    }
    private void createChanceCards() {
        if (viewHandler != null) {
            chanceCard = new ChanceCard(viewHandler);
            chanceCard.setPosX(0.5);
            chanceCard.setPosY(0.5);
            board.getChildren().add(chanceCard);
        }

    }

    // Board functions
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
    public void repositionChild2(double x, double y, ResizableStackPane child) {
//        double screenWidth = (viewHandler.getScreenDimensions()[0] - 16);
//        double screenHeight = (viewHandler.getScreenDimensions()[1] - 40 - 85);

        double screenWidth = (AppViewHandler.getScreenWidth() - child.getWidth());
        double screenHeight = (AppViewHandler.getScreenHeight() - child.getHeight()
                        - 40 - hudVBox.getHeight());

        // 16: width offscreen
        double newX = x * screenHeight + (screenWidth - screenHeight) / 2;
        // 40: height offscreen
        // 85: height of HUD
        double newY = y * screenHeight;

        child.relocate(newX, newY);
    }

    // Return 0 if movement succeeded
    // Return positive number if movement blocked by wall
    public int moveToken(Token playerToken, int moveAmount) {
        if (path != null && path.get(playerToken.getTokenLocation()).hasToken()) {
            path.get(playerToken.getTokenLocation()).removeToken(playerToken);
        }

        int newLoc = playerToken.getTokenLocation() + moveAmount;

        if (moveAmount >= 0) {
            // Check if the playerToken is at the end
            if (path != null &&
                    (path.size() - 1 <= newLoc || playerToken.getFinished())) {
                playerToken.setTokenLocation(0);
                path.get(playerToken.getTokenLocation()).addToken(playerToken);

                playerToken.setFinished(true);
                newLoc = 0;
                return 0;
            }

            // Check for a wall
            // If the move amount is negative, we won't check for a wall
            for (int i = 0; i <= moveAmount; i++) {
                if (path != null) {
                    Tile tile = path.get(playerToken.getTokenLocation() + i);

                    if (tile.hasWall() && tile.getWall().isActive()) {
                        playerToken.setTokenLocation(playerToken.getTokenLocation() + i - 1);
                        path.get(playerToken.getTokenLocation()).addToken(playerToken);
                        return moveAmount - i + 1;
                    }
                }

            }
        }

        // If there is no wall, move to the location
        playerToken.setTokenLocation(newLoc);

        if (path != null) {
            path.get(playerToken.getTokenLocation()).addToken(playerToken);
        }

        return 0;
    }

    public void changePlayerStatus(int newPlayerTurnIndex) {
        PlayerProfile prevPlayerStackPane;
        PlayerProfile currPlayerStackPane;

        if (newPlayerTurnIndex < 0) {
            throw new IllegalArgumentException();
        }

        if (playerProfileHbox != null) {
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



    }

    public Pane getBoard() {
        return board;
    }
    public Tile getTile(int tileLocation) {
        return path.get(tileLocation);
    }
    public ViewHandler getViewHandler() {
        return viewHandler;
    }
    public Tile getTileTokenOccupies(Token playerToken) {
        return path.get(playerToken.getTokenLocation());
    }

    public static void addNewNotification(Node node) {

    }


}
