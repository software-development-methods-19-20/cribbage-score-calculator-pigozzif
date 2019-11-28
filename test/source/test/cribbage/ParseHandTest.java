package test.cribbage;

import cribbage.Card;
import cribbage.CardParser;
import cribbage.Suite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParseHandTest {

    @Test
    void parseOneCard() {
        ArrayList<Card> result = CardParser.parseHand("5H");
        assertThat(result.get(0).rank(), is("5"));
        assertThat(result.get(0).suite(), is(Suite.HEART));
    }

    @Test
    void parseOneHand() {
        String[] cardStrings = {"5H", "5D", "5S", "JC", "5C"};
        ArrayList<Card> result = CardParser.parseHand("5H5D5SJC5C");
        assertThat(result.stream().map(Card::toString).toArray(), is(cardStrings));
    }
}
