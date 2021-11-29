package window.player;

public enum AwardEnum {
    RICHEST("Richest", "Have the most money by the end of the game"),
    LUCKIEST("Luckiest", "Landed on the most chance tiles throughout the game"),
    MINIGAME_MASTER("Minigame Master", "Won the most minigames");

    private final String displayName;
    private final String description;

    private AwardEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getDescription() {
        return description;
    }
}
