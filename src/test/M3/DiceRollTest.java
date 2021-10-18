package M3;

import org.junit.Test;
import window.dice.Dice;

import static org.junit.Assert.assertTrue;

public class DiceRollTest {
    @Test
    public void testDiceRoll10Max() {
        int max = 10;
        Dice dice = new Dice(max);

        for (int i = 0; i < 100; i++) {
            dice.rollDice();
            assertTrue(dice.getValue() <= max && dice.getValue() >= 1);
        }
    }

    @Test
    public void testDiceRoll20Max() {
        int max = 20;
        Dice dice = new Dice(max);

        for (int i = 0; i < 1000; i++) {
            dice.rollDice();
            assertTrue(dice.getValue() <= max && dice.getValue() >= 1);
        }
    }
}
