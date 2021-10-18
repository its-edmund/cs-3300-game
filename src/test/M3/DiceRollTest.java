package M3;

import org.junit.Test;
import window.dice.Dice;

import static org.junit.Assert.assertTrue;

public class DiceRollTest {
    @Test
    public void testDiceRollLimits() {
        int max = 10;
        Dice dice = new Dice(max);

        for (int i = 0; i < 100; i++) {
            dice.rollDice();
            assertTrue(dice.getValue() <= max && dice.getValue() >= 1);
        }
    }

    @Test
    public void testDiceRollMax() {
        for (int i = 2; i < 10; i++) {
            Dice dice = new Dice(i);
            dice.rollDice();
            assertTrue(dice.getValue() <= i && dice.getValue() >= 1);
        }
    }
}
