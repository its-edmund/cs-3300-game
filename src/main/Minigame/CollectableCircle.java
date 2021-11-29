package Minigame;


import javafx.scene.shape.Circle;

public class CollectableCircle extends Circle implements Collectable {

    boolean isCollected;

    @Override
    public boolean isActive() {
        return isVisible();
    }

    @Override
    public void setActive(boolean active) {
        setVisible(active);
    }

    @Override
    public boolean isCollected() {
        return isCollected;
    }

    @Override
    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
