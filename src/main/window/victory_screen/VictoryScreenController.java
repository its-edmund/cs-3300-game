package window.victory_screen;

import NotificationWindow.AbstractNotification;
import NotificationWindow.AwardNotification;
import core.AbstractController;
import core.AppPaths;
import core.GameStates;
import core.ViewHandler;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class VictoryScreenController extends AbstractController {

    @FXML VBox playerList;
    @FXML VBox resultList;
    @FXML VBox awardsList;

//    TableColumn playerList;
//    TableColumn resultList;
//    TableColumn awardsList;
//    @FXML TableView table;

    @FXML Text titleText;
    @FXML Text scoreText;


    public ArrayList<Player> winnerList;

    private Image winnerStar;
    private Image tiedWinnerStar;

    public VictoryScreenController(ViewHandler viewHandler) {
        super(viewHandler);
    }

    @Override
    public void initialize(URL location, ResourceBundle bundle) {

        scoreText = new Text();
        scoreText.setFont(new Font(20));

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

//        TableColumn playerList = new TableColumn();
//        TableColumn resultList = new TableColumn();
//        TableColumn awardsList = new TableColumn();
//
//        table.getColumns().addAll(playerList, resultList, awardsList);

        for (Player player : winnerList) {

            populatePlayerList(player);
            populateResults(player);
            populateAwardsList(player);
        }
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
        playerText.setFont(new Font(20));

        Token playerToken = player.getToken();

        HBox hBox = new HBox(playerToken, playerText);
        playerList.getChildren().add(hBox);
    }
    private void populateResults(Player player) {

        Text playerText = new Text();
        playerText.setFont(new Font(20));

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
                    AbstractNotification awardNotification =
                            new AwardNotification(viewHandler, award);

                    // Set Position
//                    awardNotification.setPosX(0.5);
//                    awardNotification.setPosY(0.5);

                    viewHandler.getState().addNotification(awardNotification);
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
}
