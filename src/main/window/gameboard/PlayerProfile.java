package window.gameboard;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import token.TokenIcon;
import window.player.Player;

public class PlayerProfile extends StackPane {

    Rectangle playerProfileRectangle;
    Text playerProfileText;
    TokenIcon tokenIcon;

    public PlayerProfile(Player player) {
        playerProfileRectangle = new Rectangle();
        playerProfileRectangle.setWidth(200);
        playerProfileRectangle.setHeight(50);
        playerProfileRectangle.setFill(player.getColor());
        playerProfileRectangle.setStroke(Color.BLACK);
        playerProfileRectangle.setStrokeWidth(5);

        playerProfileText = new Text();
        playerProfileText.setText(player.getName() + "\n" + "$" + player.getMoney());
        playerProfileText.textProperty().bind(Bindings.createStringBinding(
                () -> player.getName() + "\n" + "$" + player.getMoneyIntegerProperty().get(), player.getMoneyIntegerProperty())
        );

        setupTokenIcon(player);

        this.getChildren().addAll(playerProfileRectangle, playerProfileText, tokenIcon);
    }

    private void setupTokenIcon(Player player) {
        tokenIcon = new TokenIcon();
        tokenIcon.setTokenContent(player.getToken().getTokenType());
        tokenIcon.setSize(40,40);

        if (player.getColor() == Color.BLACK) {
            tokenIcon.setColor(Color.WHITE);
        } else {
            tokenIcon.setColor(Color.BLACK);
        }

        tokenIcon.setTranslateX(50);
    }

    public Rectangle getPlayerProfileRectangle() {
        return playerProfileRectangle;
    }

    public Text getPlayerProfileText() {
        return playerProfileText;
    }

    public void setPlayerProfileText(String text) {
        playerProfileText.setText(text);
    }

}
