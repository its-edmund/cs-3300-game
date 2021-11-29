package M6;

import javafx.scene.paint.Color;
import org.junit.Test;
import token.Token;
import token.TokenEnum;
import token.TokenIcon;

public class TestLoadToken {

    @Test
    public void testSVGToken() {

        for (TokenEnum tokenEnum : TokenEnum.values()) {

            TokenIcon tokenIcon = new TokenIcon();
            tokenIcon.setTokenContent(tokenEnum.getSVGShape());
        }

    }

}
