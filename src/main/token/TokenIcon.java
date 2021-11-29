package token;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class TokenIcon extends Region {

    private SVGPath svg;

    public TokenIcon() {
        svg = new SVGPath();
        this.setShape(svg);
    }

    public TokenIcon(TokenEnum tokenEnum, Color color, double width, double height) {

        // Load the SVG
        this();

        setTokenContent(tokenEnum);
        setSize(width, height);
        setColor(color);
    }

    public void setTokenContent(TokenEnum tokenEnum) {
        svg.setContent(tokenEnum.getSVGShape());
    }
    public void setTokenContent(String svgString) {
        svg.setContent(svgString);
    }

    public void setSize(double width, double height) {
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setPrefSize(width, height);
    }

    public void setColor(Color color) {
        this.setStyle(translateColorToCSS(color));
    }

    private String translateColorToCSS(Color color) {
        String colorString = "#" + color.toString().substring(2);
        String returnString = "-fx-background-color: " + colorString + ";";
        return returnString;
    }
}
