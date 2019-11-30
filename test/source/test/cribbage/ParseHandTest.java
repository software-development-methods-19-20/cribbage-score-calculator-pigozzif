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

    private static HandScorer getScorer(String handText) {
        ArrayList<Card> testHand = CardParser.parseHand(handText);
        return new HandScorer(testHand);
    }

    @Test
    void scoreRun() {
        HandScorer scorer0 = getScorer("5H2D5SJC5C");
        assertThat(scorer0.checkRuns(), is(0));
        HandScorer scorer1 = getScorer("5H6C7D9D8C");
        assertThat(scorer1.checkRuns(), is(3));
        HandScorer scorer2 = getScorer("4H7C8C9SKS");
        assertThat(scorer2.checkRuns(), is(4));
        HandScorer scorer3 = getScorer("2S3H4D5D6C");
        assertThat(scorer3.checkRuns(), is(5));
    }

    @Test
    void checkPairs() {
        HandScorer scorer = getScorer("1H2D3S4C5C");
        assertThat(scorer.scorePairs(), is(0));
        HandScorer scorer1 = getScorer("7H6C7D9D8C");
        assertThat(scorer1.scorePairs(), is(2));
        HandScorer scorer2 = getScorer("QHQC8C9SQS");
        assertThat(scorer2.scorePairs(), is(6));
        HandScorer scorer3 = getScorer("3S3H3D3DJC");
        assertThat(scorer3.scorePairs(), is(12));
        HandScorer scorer4 = getScorer("AHAC9D9D8C");
        assertThat(scorer4.scorePairs(), is(4));
        HandScorer scorer5 = getScorer("7H6C7D6D6C");
        assertThat(scorer5.scorePairs(), is(8));
    }

    @Test
    void scoreFlush() {
        HandScorer scorer = getScorer("1H2H3H4H5H");
        assertThat(scorer.checkFlush(), is(5));
        HandScorer scorer1 = getScorer("3S3HJC3DKC");
        assertThat(scorer1.checkFlush(), is(1));
        HandScorer scorer2 = getScorer("3C3CJC3CKC");
        assertThat(scorer2.checkFlush(), is(6));
    }

    @Test
    void scoreFifteenTwos() {
        HandScorer scorer = getScorer("1H2D3S4C5C");
        assertThat(scorer.checkFifteenTwos(), is(2));
        HandScorer scorer1 = getScorer("7H6C7D9D8C");
        assertThat(scorer1.checkFifteenTwos(), is(6));
        HandScorer scorer2 = getScorer("AHQC5D8D5C");
        assertThat(scorer2.checkFifteenTwos(), is(4));
    }

    @Test
    void totalScore() {
        HandScorer scorer = getScorer("5H5D5SJC5C");
        assertThat(scorer.scoreHand(), is(29));
        HandScorer scorer1 = getScorer("0DJHQSAC9D");
        // THIS TEST FAILS. SHOULDN'T THE SCORE BE 0? WHY IS THERE A 0 IN THE HAND?
        assertThat(scorer1.scoreHand(), is(4));
    }
}
