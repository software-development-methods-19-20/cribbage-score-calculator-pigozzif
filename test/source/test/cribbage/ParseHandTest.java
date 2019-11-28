package test.cribbage;

import cribbage.Card;
import cribbage.CardParser;
import cribbage.HandScorer;
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

    @Test
    void testValues() {
        Card c1 = new Card("5", "H");
        assertThat(c1.getValue(), is(5));
        Card c2 = new Card("A", "H");
        assertThat(c2.getValue(), is(1));
        Card c3 = new Card("Q", "H");
        assertThat(c3.getValue(), is(10));
    }

    @Test
    void scoreRun() {
        ArrayList<Card> testHand0 = CardParser.parseHand("5H2D5SJC5C");
        assertThat(HandScorer.checkRuns(testHand0), is(0));
        ArrayList<Card> testHand1 = CardParser.parseHand("5H6C7D9D8C");
        assertThat(HandScorer.checkRuns(testHand1), is(3));
        ArrayList<Card> testHand2 = CardParser.parseHand("4H7C8C9SKS");
        assertThat(HandScorer.checkRuns(testHand2), is(4));
        ArrayList<Card> testHand3 = CardParser.parseHand("2S3H4D5D6C");
        assertThat(HandScorer.checkRuns(testHand3), is(5));
    }


}
