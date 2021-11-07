package core;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

public class ResizableStackPane extends StackPane implements Resizable {

    protected double posX = 0;
    protected double posY = 0;

    public ResizableStackPane() {
        super();

        this.setVisible(false);
        AppViewHandler.addNewNodeToResize(this);
    }

    @Override
    public void onResize() {

        if (!this.justAddedToScene()) {
            this.setVisible(true);
        }

        this.setScaleX(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);
        this.setScaleY(AppViewHandler.getScreenHeight() / AppViewHandler.INITIAL_SCREEN_HEIGHT);

        this.relocate(getPosX(), getPosY());

//        double screenWidth = AppViewHandler.getScreenWidth()  - 16;
//        double screenHeight =
//                (AppViewHandler.getScreenHeight() - 40 - 85);
//
//        // 16: width offscreen
//        double newX = posX * screenHeight + (screenWidth - screenHeight) / 2;
//        // 40: height offscreen
//        // 85: height of HUD
//        double newY = posY * screenHeight;
//
//        this.relocate(newX, newY);
    }

    @Override
    public boolean justAddedToScene() {
        return this.getHeight() == 0 || this.getWidth() == 0;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }

}
