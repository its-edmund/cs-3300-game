package NotificationWindow;

import core.GameStates;
import core.ViewHandler;
import javafx.scene.paint.Color;
import window.player.Player;

import java.util.Random;

public class ChanceCardNotification2 extends AbstractClickNotification {

    private final int NUM_CHANCE_CARDS = 6;
    private int seed;
    private String[] chanceCardTextDescriptions = {
            "Payday! You win $300!",
            "Oh no! You lose $100!",
            "Advance 3 squares",
            "Advance 1 squares",
            "Go back 2 squares",
            "Go back 1 squares"
    };

    public ChanceCardNotification2(ViewHandler viewHandler) {
        super(viewHandler);

        notificationBox.setWidth(120);
        notificationBox.setHeight(75);
        notificationBox.setFill(Color.CORNFLOWERBLUE);

        seed = (new Random()).nextInt(NUM_CHANCE_CARDS);
        notificationText.setText(chanceCardTextDescriptions[seed]);
    }

    @Override
    public void onExit() {

        Player player = viewHandler.getState().getPlayerController().getCurrentPlayer();

        if (seed == 0) {
            // add money
            player.setMoney(player.getMoney() + 300);
            viewHandler.getState().updateState(GameStates.END_TURN);

        } else if ( seed == 1) {
            // subtract money
            player.setMoney(player.getMoney() - 100);
            viewHandler.getState().updateState(GameStates.END_TURN);


        } else if (seed == 2) {

            // advance 3 squares
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(4);
            viewHandler.getState().updateState(GameStates.MOVING);


        } else if (seed == 3) {

            //advance 1 squares
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(1);
            viewHandler.getState().updateState(GameStates.MOVING);

        } else if (seed == 4) {

            // go back 2 squares
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(-2);
            viewHandler.getState().updateState(GameStates.MOVING);

        } else if (seed == 5) {

            // go back 1 square
            viewHandler.getState().getPlayerController().getCurrentPlayer().getPlayerMover().setRemainingMoves(-1);
            viewHandler.getState().updateState(GameStates.MOVING);

        }
    }

}
