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
import token.Token;
import token.TokenEnum;
import token.TokenIcon;
import window.dice.Dice;
import window.player.Player;
import window.player.PlayerController;

import java.net.URL;
import java.util.ArrayList;
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

        if (viewHandler != null) {
            for (Player player : viewHandler.getState().getPlayerController().getPlayers()) {
                player.setupPlayerMover(this);
            }
        }
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
                                resizableNode.getRawPosX(),
                                resizableNode.getRawPosY()
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
                                resizableNode.getRawPosX(),
                                resizableNode.getRawPosY()
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

        path.get(0).setType(TileType.END);

        setupChanceTile(path.get(11));
        setupChanceTile(path.get(19));
        setupChanceTile(path.get(26));
        setupChanceTile(path.get(34));
        setupChanceTile(path.get(41));
        setupChanceTile(path.get(49));

        path.get(2).setType(TileType.LAUNCH_MINIGAME);
        path.get(3).setType(TileType.LAUNCH_MINIGAME);
        path.get(15).setType(TileType.LAUNCH_MINIGAME);
        path.get(25).setType(TileType.LAUNCH_MINIGAME);
        path.get(30).setType(TileType.LAUNCH_MINIGAME);
        path.get(42).setType(TileType.LAUNCH_MINIGAME);
        path.get(51).setType(TileType.LAUNCH_MINIGAME);
        path.get(53).setType(TileType.LAUNCH_MINIGAME);

        path.get(7).addWall(90);
        path.get(21).addWall(0);
        path.get(28).addWall(0);
        path.get(33).addWall(90);
        path.get(43).addWall(90);
        path.get(56).addWall(0);

        // Add all player tokens to the first square
        PlayerController playerController;
        if (viewHandler != null) {
            playerController = viewHandler.getState().getPlayerController();
        } else {
            playerController = new PlayerController();
        }

        setupPlayerTokens();

        // DEBUG
//        testTokenSizes();

        // Win screen debug
//        playerController.getCurrentPlayer().getPlayerMover().setRemainingMoves(59);
    }

    private void setupChanceTile(Tile tile) {
        tile.setType(TileType.CHANCE);

        ResizableStackPane pane = new ResizableStackPane();
        pane.setViewOrder(ViewOrder.BACKGROUND);

        TokenIcon questionMark = new TokenIcon();
        questionMark.setTokenContent(SVGShapes.QUESTION_MARK);
        questionMark.setColor(Color.BLACK);
        questionMark.setSize(8, 8);

        pane.getChildren().add(questionMark);
        board.getChildren().add(pane);

        pane.setPosX(tile.getPosX());
        pane.setPosY(tile.getPosY());
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

            chanceCard.setViewOrder(ViewOrder.BACKGROUND);

            chanceCard.setPosX(0.5);
            chanceCard.setPosY(0.5);
            board.getChildren().add(chanceCard);
        }

    }

    private void testTokenSizes() {
        Tile tile = new Tile(0.2, 0.5, this);
        tile.setPosX(0.3);
        tile.setPosY(0.5);
        tile.setViewOrder(ViewOrder.BACKGROUND);
        board.getChildren().add(tile);

        Token token = new Token(Color.BLUE, viewHandler);
        token.setTokenType(TokenEnum.BOAT);
        board.getChildren().add(token);
        token.setCurrentTile(tile);
    }

    private void setupPlayerTokens() {

        PlayerController playerController = viewHandler.getState().getPlayerController();

        // Trying to change
        for (Player player : playerController.getPlayers()) {

            Token currToken = player.getToken();
            board.getChildren().add(currToken);
            currToken.setCurrentTile(path.get(0));
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
//        if (path != null && path.get(playerToken.getTokenLocation()).hasToken()) {
//            path.get(playerToken.getTokenLocation()).removeToken(playerToken);
//        }

        int newLoc = playerToken.getTokenLocation() + moveAmount;

        if (moveAmount >= 0) {
            // Check if the playerToken is at the end
            if (path != null &&
                    (path.size() - 1 < newLoc || playerToken.getFinished())) {
                playerToken.setTokenLocation(0);
//                path.get(playerToken.getTokenLocation()).addToken(playerToken);
                playerToken.setCurrentTile(path.get(0));

                playerToken.setFinished(true);
                newLoc = 0;
                return 0;
            }

            // Check for a wall
            // If the move amount is negative, we won't check for a wall
            for (int i = 1; i <= moveAmount; i++) {
                if (path != null) {
                    Tile tile = path.get(playerToken.getTokenLocation() + i);
                    Tile prevTile = path.get(playerToken.getTokenLocation() + i - 1);

                    if (tile.hasWall() && tile.getWall().isActive()) {
                        playerToken.setTokenLocation(playerToken.getTokenLocation() + i - 1);
//                        path.get(playerToken.getTokenLocation()).addToken(playerToken);
                        playerToken.setCurrentTile(prevTile);

                        return moveAmount - i + 1;
                    }
                }
            }
        }

        // If there is no wall, move to the location
        playerToken.setTokenLocation(newLoc);

        Tile tile = path.get(newLoc);
        playerToken.setCurrentTile(tile);

//        if (path != null) {
//            path.get(playerToken.getTokenLocation()).addToken(playerToken);
//        }

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
    public Tile getTile(int tileLocation) {
        return path.get(tileLocation);
    }
    public ViewHandler getViewHandler() {
        return viewHandler;
    }
    public Tile getTileTokenOccupies(Token playerToken) {
        return path.get(playerToken.getTokenLocation());
    }


}
