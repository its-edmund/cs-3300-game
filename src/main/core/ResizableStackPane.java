package core;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

public class ResizableStackPane extends StackPane implements Resizable {

    protected double posX = 0;
    protected double posY = 0;
    private double centerX = 0;
    private double centerY = 0;

    @Override
    public void onResize() {

        this.setScaleX(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);
        this.setScaleY(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);

        double screenWidth = AppViewHandler.getScreenWidth()  - 16;
        double screenHeight =
                (AppViewHandler.getScreenHeight() - 40 - 85);

        // 16: width offscreen
        double newX = posX * screenHeight + (screenWidth - screenHeight) / 2;
        // 40: height offscreen
        // 85: height of HUD
        double newY = posY * screenHeight;

        this.relocate(newX, newY);
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    public void setCenterX(double centerX) {this.centerX = centerX; }
    public void setCenterY(double centerY) {this.centerY = centerY; }

    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public double getCenterX() { return centerX; }
    public double getCenterY() { return centerY; }

}
