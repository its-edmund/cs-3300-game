package window.dice;

import java.util.Random;

public class Dice {
    private int value;
    private Random random = new Random();
    private int max;

    public Dice(int max) {
        this.max = max;
    }

    public int getValue() {
        return this.value;
    }

    public void rollDice() {
        value = random.nextInt(max) + 1;
    }
}
