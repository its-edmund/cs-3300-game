package core;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

public class ResizableStackPane extends StackPane implements Resizable{

    private double posX = 0.5;
    private double posY = 0.5;

    @Override
    public void onResize() {
//        for (Node child: this.getChildren()) {
//            if (child instanceof Shape) {
//                child.setScaleX(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);
//                child.setScaleY(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);
//            }
//        }

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

    public double getPosX() {
        return posX;
    }

    public void setPosX() {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY() {
        this.posY = posY;
    }
}
