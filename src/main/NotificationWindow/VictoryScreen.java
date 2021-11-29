package NotificationWindow;

import core.AppPaths;
import core.GameStates;
import core.ViewHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import token.Token;
import window.player.Award;
import window.player.AwardEnum;
import window.player.Player;
import window.player.PlayerController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VictoryScreen extends AbstractNotification {

    VBox playerList;
    VBox resultList;
    VBox awardsList;

    Text titleText;

    public ArrayList<Player> winnerList;

    private final double WIDTH = 500;
    private final double HEIGHT = 350;

    private Image winnerStar;
    private Image tiedWinnerStar;

    public VictoryScreen(ViewHandler viewHandler) {
        super(viewHandler);

        setNotificationWidth(WIDTH);
        setNotificationHeight(HEIGHT);

        setPosX(0.5);
        setPosY(0.5);

        playerList = new VBox();
        resultList = new VBox();
        awardsList = new VBox();

        try {
            loadStars();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Rank the players based off of how far they got on the board
        winnerList = new ArrayList<>();

        determineWinner();
        determineAwards();

        setTitle();
        setColumnHeadings();

        for (Player player : winnerList) {

            populatePlayerList(player);
            populateResults(player);
            populateAwardsList(player);
        }

        HBox hBox = new HBox(playerList, resultList, awardsList);
        hBox.setSpacing(50);
        setNotificationText(titleText, hBox);

    }

    public void loadStars() throws FileNotFoundException {
        FileInputStream is1;
        is1 = new FileInputStream( "resources/" + AppPaths.IMG_PATH + "star_icon.png");
        winnerStar = new Image(is1);

        FileInputStream is2;
        is2 = new FileInputStream( "resources/" + AppPaths.IMG_PATH + "orange_star_icon.png");
        tiedWinnerStar = new Image(is2);
    }

    public void determineWinner() {
        PlayerController playerController
                = viewHandler.getState().getPlayerController();

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
    }
    public void determineAwards() {
        PlayerController playerController
                = viewHandler.getState().getPlayerController();

        for (AwardEnum awardType : AwardEnum.values()) {
            playerController.giveAward(awardType);
        }
    }

    private void setTitle() {
        titleText = new Text();
        titleText.setFont(new Font(28));
        titleText.setText("SCORE SUMMARY");
    }
    private void setColumnHeadings() {
        // does nothing for now
    }

    private void populatePlayerList(Player player) {

        Text playerText = new Text();
        playerText.setText(player.getName());
        playerText.setFont(new Font(16));

        Token playerToken = player.getToken();

        HBox hBox = new HBox(playerToken, playerText);
        playerList.getChildren().add(hBox);
    }
    private void populateResults(Player player) {

        Text playerText = new Text();
        playerText.setFont(new Font(16));

        if (player.getToken().getFinished()) {
            playerText.setText("WINNER!" +
                    " Traversed 60 squares.\n");
        } else {
            playerText.setText("Traversed "
                    + player.getToken().getTokenLocation() + " squares.\n");
        }

        resultList.getChildren().add(playerText);
    }
    private void populateAwardsList(Player player) {

        HBox hBox = new HBox();


        for (Award award : player.getAwards()) {
            ImageView awardView;

            if (award.isSoleWinner()) {
                awardView = createStarIcon(winnerStar);
            } else {
                awardView = createStarIcon(tiedWinnerStar);
            }

            //Adding event Filter
            awardView.addEventFilter(MouseEvent.MOUSE_CLICKED, getAwardNotificationHandler(award));
            hBox.getChildren().add(awardView);
        }

        awardsList.getChildren().add(hBox);
    }

    private ImageView createStarIcon(Image star) {
        ImageView imageView = new ImageView(star);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        return imageView;
    }

    private EventHandler<MouseEvent> getAwardNotificationHandler(Award award) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                System.out.println("Award clicked!");

                if (viewHandler.getState().getCurrentState() != GameStates.VICTORY_SCREEN_AWARD_NOTIFICATION) {
                    viewHandler.getState().setCurrentAward(award);
                    viewHandler.getState().updateState(GameStates.VICTORY_SCREEN_AWARD_NOTIFICATION);
                }

            }
        };

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
    public void onExit() {

    }
}
