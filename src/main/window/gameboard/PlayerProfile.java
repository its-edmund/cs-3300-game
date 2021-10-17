package window.gameboard;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import window.player.Player;

public class PlayerProfile extends StackPane {

    Rectangle playerProfileRectangle;
    Text playerProfileText;

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

        this.getChildren().addAll(playerProfileRectangle, playerProfileText);
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
