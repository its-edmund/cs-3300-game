package M3;

import org.junit.Test;
import window.dice.Dice;

import static org.junit.Assert.assertTrue;

public class DiceRollTest {
    @Test
    public void testDiceRoll() {
        int max = 10;
        Dice dice = new Dice(max);

        dice.rollDice();
        assertTrue(dice.getValue() <= max && dice.getValue() >= 1);
    }
}
