package window.config_screen;

import core.AbstractController;
import core.AppViewHandler;
import core.ViewHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import state.State;
import window.player.Player;
import window.player.PlayerController;
import token.TokenEnum;

public class ConfigurationController extends AbstractController {

    @FXML private ToggleGroup playerCountBtns;
    @FXML private Button submitPlayersBtn;
    @FXML private HBox playerNames;
    @FXML private HBox colorPickers;
    @FXML private HBox tokenPickers;
    @FXML private RadioButton two;
    @FXML private RadioButton three;
    @FXML private RadioButton four;
    @FXML private Text playerOrder;
    @FXML private Text startingMoney;
    @FXML private Button startGame;

    public ArrayList<Player> players;
    private Color[] defaultColors = {Color.DARKGREEN, Color.BLUE, Color.VIOLET, Color.RED};

    public ConfigurationController(ViewHandler viewHandler) {
        super(viewHandler);

        players = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {

        for (int i = 1; i <= 2; i++) {
            // Initialize text field
            TextField t = new TextField();
            t.setPromptText(("Player " + Integer.toString(i) + " name"));

            // Initialize ColorPicker
            ColorPicker cp = new ColorPicker();
            cp.setValue(defaultColors[i - 1]);

            ComboBox<TokenEnum> tokenEnumComboBox = initializeComboBox();

            playerNames.getChildren().add(t);
            colorPickers.getChildren().add(cp);
            tokenPickers.getChildren().add(tokenEnumComboBox);
        }

        playerCountBtns.selectedToggleProperty().addListener(e-> {
            if (playerCountBtns.getSelectedToggle() != null) {
                playerNames.getChildren().clear();
                colorPickers.getChildren().clear();
                ToggleButton btn = (ToggleButton) playerCountBtns.getSelectedToggle();
                int p = btn.getText().charAt(0) - '0';
                for (int i = 1; i <= p; i++) {
                    ColorPicker cp = new ColorPicker();
                    cp.setValue(defaultColors[i - 1]);

                    TextField t = new TextField();
                    t.setPromptText(("Player " + Integer.toString(i) + " name"));
                    playerNames.getChildren().add(t);
                    colorPickers.getChildren().add(cp);
                }
            }
        });

        startGame.setOnAction(e -> {

            players = new ArrayList<>();
            HashSet<String> seenNames = new HashSet<>();
            HashSet<Color> seenColors = new HashSet<>();
            HashSet<TokenEnum> seenTokens = new HashSet<>();

            int i = 0;
            for (Node node : playerNames.getChildren()) {
                TextField t = (TextField) node;
                ColorPicker cp = (ColorPicker) colorPickers.getChildren().get(i);
                ComboBox<TokenEnum> tokenEnumComboBox
                        = (ComboBox<TokenEnum>) tokenPickers.getChildren().get(i);

                String text = t.getText();
                Color color = cp.getValue();
                TokenEnum tokenEnum = tokenEnumComboBox.getValue();

                if ((!(text == null ||
                        text.trim().isEmpty() ||
                        seenNames.contains(text) ||
                        color == null ||
                        seenColors.contains(color) ||
                        tokenEnum == null ||
                        seenTokens.contains(tokenEnum)))) {

                    Player player = new Player(text,
                            color,
                            10000,
                            (AppViewHandler) viewHandler);

                    player.getToken().setTokenType(tokenEnum);
                    player.setupPlayerTurnSound();

                    players.add(player);

                    seenNames.add(text);
                    seenColors.add(color);
                    seenTokens.add(tokenEnum);

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Please enter valid distinct names, colors, and tokens!");
                    a.show();
                    return;
                }

                i++;
            }

            // Show money and player order after submit
            Collections.shuffle(players);
            PlayerController playerController = new PlayerController();

            for (Player player : players) {
                playerController.addPlayer(player);
            }

            // Update the game state
            State state = viewHandler.getState();
            state.setPlayerController(playerController);
            viewHandler.updateState(state);

            if (!(players.size() == 0)) {
                try {
                    viewHandler.launchGameboard();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


    private ComboBox<TokenEnum> initializeComboBox() {

        ComboBox<TokenEnum> tokenEnumComboBox = new ComboBox<>();

        for (TokenEnum tokenEnum : TokenEnum.values())
            tokenEnumComboBox.getItems().add(tokenEnum);

        tokenEnumComboBox.setCellFactory(new Callback<ListView<TokenEnum>, ListCell<TokenEnum>>() {
            @Override
            public ListCell<TokenEnum> call(ListView<TokenEnum> tokenEnumListView) {
                return new ListCell<TokenEnum>() {

                    private final Rectangle rectangle;

                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        rectangle = new Rectangle(10, 10);
                    }


                    @Override
                    protected void updateItem(TokenEnum tokenEnum, boolean empty) {
                        super.updateItem(tokenEnum, empty);

                        if (tokenEnum == null || empty) {
                            setGraphic(null);
                        } else {
                            rectangle.setFill(tokenEnum.getColor());
                            setGraphic(rectangle);
                        }
                    }
                };
            }
        });

        return tokenEnumComboBox;
    }
}
