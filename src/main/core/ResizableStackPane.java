package core;

import javafx.scene.layout.StackPane;

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

        this.relocate(getRawPosX(), getRawPosY());
    }

    @Override
    public boolean justAddedToScene() {
        return this.getHeight() == 0 || this.getWidth() == 0;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        this.relocate(getRawPosX(), getRawPosY());
    }
    public void setPosY(double posY) {
        this.posY = posY;
        this.relocate(getRawPosX(), getRawPosY());
    }

    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }

    public double getRawPosX() {
        double screenWidth = (AppViewHandler.getScreenWidth() - this.getWidth());
        double screenHeight = (AppViewHandler.getScreenHeight() - this.getHeight()
                - 40 - 85);
        double newX = posX * screenHeight + (screenWidth - screenHeight) / 2;

        return newX;
    }
    public double getRawPosY() {
        double screenHeight = (AppViewHandler.getScreenHeight() - this.getHeight()
                - 40 - 85);
        double newY = posY * screenHeight;

        return newY;
    }

}
