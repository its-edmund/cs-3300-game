package window.victory_screen;

import core.AbstractController;
import core.ViewHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import window.player.Player;
import window.player.PlayerController;

import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class VictoryScreenController extends AbstractController {

    @FXML VBox playerList;
    @FXML Text scoreText;
    public ArrayList<Player> winnerList;

    public VictoryScreenController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    // Testing method
    public void initWinnerList(boolean winner) {
        winnerList = new ArrayList<>();
        Player player1 = new Player("1", null, 1, null);
        Player player2 = new Player("2", null, 2, null);
        Player player3 = new Player("3", null, 3, null);
        Player player4 = new Player("4", null, 4, null);
        if (winner) {
            player1.getToken().setTokenLocation(1);
        }
        winnerList.add(player1);
        winnerList.add(player2);
        winnerList.add(player3);
        winnerList.add(player4);
        Collections.sort(winnerList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o1.getToken().getTokenLocation(),
                        o2.getToken().getTokenLocation());
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        PlayerController playerController
                = viewHandler.getState().getPlayerController();

        scoreText = new Text();
        scoreText.setFont(new Font(20));

        // Rank the players based off of how far they got on the board
        winnerList = new ArrayList<>();

        for (Player player : playerController.getPlayers()) {
            winnerList.add(player);
        }

        Collections.sort(winnerList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o1.getToken().getTokenLocation(),
                        o2.getToken().getTokenLocation());
            }
        });

        Text titleText = new Text();
        titleText.setFont(new Font(20));
        titleText.setText("SCORE SUMMARY");
        playerList.getChildren().addAll(titleText);


        for (Player player : winnerList) {
            Text playerText = new Text();

            if (player.getToken().getFinished()) {
                playerText.setText("     " + player.getName() + "     ---     WINNER!" +
                        "traversed 60 squares.\n");
            } else {
                playerText.setText("     " + player.getName() + " traversed "
                        + player.getToken().getTokenLocation() + " squares.\n");
            }

            HBox playerHBox = new HBox();
            playerHBox.setAlignment(Pos.CENTER);
            playerHBox.getChildren().add(player.getToken());
            playerHBox.getChildren().add(playerText);

            playerList.getChildren().addAll(playerHBox);
        }
    }
}
