package window.player;

public class Award {

    AwardEnum awardType;
    boolean isSoleWinner;

    public Award(AwardEnum awardEnum, boolean isSoleWinner) {
        this.awardType = awardEnum;
        this.isSoleWinner = isSoleWinner;
    }

    public String getAwardName() {
        return awardType.getDisplayName();
    }

    public String getAwardDescription() {
        return awardType.getDescription();
    }

    public boolean isAwardOfType(AwardEnum awardType) {
        return this.awardType == awardType;
    }

    public boolean isSoleWinner() {
        return isSoleWinner;
    }

}
