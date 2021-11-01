package window.player;

import core.AbstractMoveMediator;
import core.AppViewHandler;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tile.Tile;
import token.Token;
import window.gameboard.GameboardController;

import java.util.ArrayList;

public class Player extends Circle {

    // For now, player representation is a circle
    private static float radius = 20.0f;
    private int x;
    private int y;
    private int locationLimit = 15;
    private Token playerToken;
    private PlayerMover playerMover;

    private String name;
    private Color color;
    private IntegerProperty money;

    public Player() {
        this(0, 0);
    }

    public Player(String name, Color color, int money, AppViewHandler viewHandler) {
        this(0,0);
        this.name = name;
        this.color = color;
        this.money = new SimpleIntegerProperty(money);

        playerToken = new Token(color, viewHandler);
    }

    public Player(int x, int y) {
        super(x, y, radius);
        this.x = x;
        this.y = y;
        this.money = new SimpleIntegerProperty(1000);
    }

    // Getters and Setters
    public void setLocationLimit(int limit) {
        this.locationLimit = limit;
    }
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
    public int getCurrentLocation() {
        return this.getToken().getTokenLocation();
    }
    public PlayerMover getPlayerMover() {
        return playerMover;
    }
}
