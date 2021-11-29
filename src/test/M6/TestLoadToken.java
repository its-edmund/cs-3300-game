package M6;

import javafx.scene.paint.Color;
import org.junit.Test;
import token.Token;
import token.TokenEnum;

public class TestLoadToken {

    @Test
    public void testSVGToken() {

        for (TokenEnum tokenEnum : TokenEnum.values()) {

            Token token = new Token(Color.BLUE, null);
            token.setTokenType(tokenEnum);
        }

    }

}
