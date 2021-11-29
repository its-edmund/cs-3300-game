package token;

import core.AppViewHandler;
import core.ResizableStackPane;
import core.ViewHandler;
import core.ViewOrder;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import tile.Tile;

public class Token extends ResizableStackPane {

    private int tokenLocation;
    private boolean isFinished;

    private Tile currentTile;

    private TokenEnum tokenType;
    private SVGPath svg;
    private Region svgShape;

    private Color color;



    public Token(Color color, ViewHandler viewHandler) {
        super();

        this.color = color;

        this.setViewOrder(ViewOrder.FOREGROUND);

//        setScaleX(0.4);
//        setScaleY(0.4);
//        setRotate(-45);

        this.tokenLocation = 0;

        isFinished = false;
    }


    // Getters and Setters
    public int getTokenLocation() {
        return tokenLocation;
    }
    public boolean getFinished() {
        return isFinished;
    }
    public TokenEnum getTokenType() {
        return tokenType;
    }

    public void setTokenLocation(int tokenLocation) {
        this.tokenLocation = tokenLocation;
    }
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    public void setTokenType(TokenEnum tokenType) {
        this.tokenType = tokenType;

        setupTokenShape();
    }

    private void setupTokenShape() {

//        svg.setScaleX(tokenType.getScaleX());
//        svg.setScaleY(tokenType.getScaleY());

//        svg = new SVGPath();
//        svg.setContent(tokenType.getSVGShape());
//        svg.setFill(Color.BLACK);
//
//        final Region svgShape = new Region();
//        svgShape.setShape(svg);
//        svgShape.setMinSize(100, 100);
//        svgShape.setPrefSize(100,100);
//        svgShape.setMaxSize(100, 100);
////        svgShape.setStyle("-fx-background-color: black;");
//        svgShape.setStyle(translateColorToCSS());
//
//        this.getChildren().add(svgShape);

        svgShape = new TokenIcon(tokenType, color, 10, 10);
        this.getChildren().add(svgShape);
    }

    private String translateColorToCSS() {
        String colorString = "#" + color.toString().substring(2);
        String returnString = "-fx-background-color: " + colorString + ";";
        return returnString;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;

        this.setPosX(currentTile.getPosX());
        this.setPosY(currentTile.getPosY());
    }
}
