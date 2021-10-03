package window.start;

import core.AbstractController;
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
import javafx.scene.text.Text;
import window.player.Player;

public class ConfigurationController extends AbstractController {

    @FXML
    private ToggleGroup playerCountBtns;
    @FXML
    private Button submitPlayersBtn;
    @FXML
    private HBox playerNames;
    @FXML
    private HBox colorPickers;
    @FXML
    private RadioButton two;
    @FXML
    private RadioButton three;
    @FXML
    private RadioButton four;
    @FXML
    private Text playerOrder;
    @FXML
    private Text startingMoney;
    @FXML
    private Button startGame;

    public ArrayList<Player> players;

    public ConfigurationController(ViewHandler viewHandler) {
        super(viewHandler);
        playerCountBtns = new ToggleGroup();
        submitPlayersBtn = new Button();
        playerNames = new HBox();
        two = new RadioButton();
        three = new RadioButton();
        four = new RadioButton();
        playerOrder = new Text();
        startingMoney = new Text();
        startGame = new Button();
        players = new ArrayList<>();
        colorPickers = new HBox();
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {

        for (int i = 1; i <= 2; i++) {
            TextField t = new TextField();
            ColorPicker cp = new ColorPicker();
            t.setPromptText(("Player " + Integer.toString(i) + " name"));
            playerNames.getChildren().add(t);
            colorPickers.getChildren().add(cp);
        }
        playerCountBtns.selectedToggleProperty().addListener(e-> {
            if (playerCountBtns.getSelectedToggle() != null) {
                playerNames.getChildren().clear();
                colorPickers.getChildren().clear();
                ToggleButton btn = (ToggleButton) playerCountBtns.getSelectedToggle();
                int p = btn.getText().charAt(0) - '0';
                for (int i = 1; i <= p; i++) {
                    ColorPicker cp = new ColorPicker();
                    TextField t = new TextField();
                    t.setPromptText(("Player " + Integer.toString(i) + " name"));
                    playerNames.getChildren().add(t);
                    colorPickers.getChildren().add(cp);
                }
            }
        });
        submitPlayersBtn.setOnAction(e -> {
            players = new ArrayList<>();
            HashSet<String> seenNames = new HashSet<>();
            HashSet<Color> seenColors = new HashSet<>();
            int i = 0;
            for (Node node : playerNames.getChildren()) {
                TextField t = (TextField) node;
                ColorPicker cp = (ColorPicker) colorPickers.getChildren().get(i);
                String text = t.getText();
                Color color = cp.getValue();
                if ((!(text == null ||
                        text.trim().isEmpty() ||
                        seenNames.contains(text) ||
                        color == null ||
                        seenColors.contains(color)))){
                    players.add(new Player(text, color));
                    seenNames.add(text);
                    seenColors.add(color);
                } else {
                    playerOrder.setText("");
                    startingMoney.setText("");
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Please enter valid distinct names and colors");
                    a.show();
                    return;
                }
                i++;
            }

            // Show money and player order after submit
            Collections.shuffle(players);
            String pOrder = "Player Order: ";
            for (int j = 1; j < players.size() + 1; j++) {
                pOrder += Integer.toString(j) + ". " + players.get(j - 1).name +
                        "(Color: " + players.get(j - 1).color.toString() + ")  ";
            }
            playerOrder.setText(pOrder);
            startingMoney.setText("Starting Money: $1000");
        });
        startGame.setOnAction(e -> {
                if (!(players.size() == 0)) {
                        try {
                            viewHandler.launchGameboard();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

    }
}
