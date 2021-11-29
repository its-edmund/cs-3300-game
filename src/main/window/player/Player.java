package window.player;

import core.AppViewHandler;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tile.Tile;
import token.Token;
import token.TokenEnum;
import window.gameboard.GameboardController;

import java.io.File;
import java.util.ArrayList;

public class Player extends Circle {

    // For now, player representation is a circle
    private static float radius = 20.0f;
    private Token playerToken;
    private PlayerMover playerMover;

    private Media newTurnSFX;

    private String name;
    private Color color;
    private IntegerProperty money;

    private int minigameScore;
    private int numMinigamesWon;
    private int numChanceTilesLandedOn;

    private ArrayList<Award> awards = new ArrayList<>();

    public Player() {
        this(0, 0);
    }

    public Player(String name, Color color, int money, AppViewHandler viewHandler) {
        this(0,0);
        this.name = name;
        this.color = color;
        this.money = new SimpleIntegerProperty(money);

        numMinigamesWon = 0;
        numChanceTilesLandedOn = 0;

        playerToken = new Token(color, viewHandler);
    }

    public Player(int x, int y) {
        super(x, y, radius);
        this.money = new SimpleIntegerProperty(1000);
    }

    // Getters and Setters
    public void setupPlayerMover(GameboardController gameboardController) {
        playerMover = new PlayerMover(this, gameboardController);
    }
    public String getName() {
        return name;
    }
    public Color getColor() {return color; }
    public int getMoney() {
        return money.get();
    }
    public Tile getBlockingTile() {
        if (playerMover != null) {
            return playerMover.getNextTile();
        } else {
            throw new RuntimeException("PlayerMover is undefined!\n");
        }
    }
    public IntegerProperty getMoneyIntegerProperty() {
        return money;
    }
    public void setMoney(int money) {
        this.money.set(money);
    }
    public Token getToken() {
        return playerToken;
    }

    public double getMinigameScore() {
        return minigameScore;
    }
    public void setMinigameScore(int score) {
        this.minigameScore = score;
    }

    public int getCurrentLocation() {
        return this.getToken().getTokenLocation();
    }
    public PlayerMover getPlayerMover() {
        return playerMover;
    }

    // Award based methods
    public int getNumMinigamesWon() {
        return numMinigamesWon;
    }

    public void addAward(Award award) {
        awards.add(award);
    }

    public ArrayList<Award> getAwards() {
        return awards;
    }

    public boolean hasAward(AwardEnum awardType) {
        boolean result = false;

        for (Award award : awards) {
            if (award.isAwardOfType(awardType)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void setNumMinigamesWon(int numMinigamesWon) {
        this.numMinigamesWon = numMinigamesWon;
    }

    public int getNumChanceTilesLandedOn() {
        return numChanceTilesLandedOn;
    }

    public void setNumChanceTilesLandedOn(int numChanceTilesLandedOn) {
        this.numChanceTilesLandedOn = numChanceTilesLandedOn;
    }

    public void setupPlayerTurnSound() {
        TokenEnum tokenEnum = playerToken.getTokenType();
        newTurnSFX = new Media(new File(tokenEnum.getSoundFilepath()).toURI().toString());
    }

    public void playNewTurnSound() {
        MediaPlayer mediaPlayer = new MediaPlayer(newTurnSFX);
        mediaPlayer.play();
    }
}
