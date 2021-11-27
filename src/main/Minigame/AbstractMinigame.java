package Minigame;

public abstract class AbstractMinigame {

    protected int selectedMinigame;

    public abstract String getMinigameTitle();
    public abstract String getMinigameDescription();
    public abstract void playMinigame();

}
