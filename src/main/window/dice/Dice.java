package window.dice;

import java.util.Random;

public class Dice {
    private int value;
    private final int MAX;

    private Random random = new Random();

    public Dice(int max) {
        this.MAX = max;
    }

    public int getValue() {
        return this.value;
    }

    public void rollDice() {
        value = random.nextInt(MAX) + 1;
    }
}
