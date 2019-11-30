package test.cribbage;

import cribbage.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
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
    void checkRun() {
        Runs scorer0 = HandScorer.createScorer(Runs::new);
        ArrayList<Card> hand0 = CardParser.parseHand("5H2D5SJC5C");
        assertThat(scorer0.score(hand0), is(0));
        Runs scorer1 = HandScorer.createScorer(Runs::new);
        ArrayList<Card> hand1 = CardParser.parseHand("5H6C7D9D8C");
        assertThat(scorer1.score(hand1), is(3));
        Runs scorer2 = HandScorer.createScorer(Runs::new);
        ArrayList<Card> hand2 = CardParser.parseHand("4H7C8C9SKS");
        assertThat(scorer2.score(hand2), is(4));
        Runs scorer3 = HandScorer.createScorer(Runs::new);
        ArrayList<Card> hand3 = CardParser.parseHand("2S3H4D5D6C");
        assertThat(scorer3.score(hand3), is(5));
    }

    @Test
    void checkPairs() {
        Pairs scorer0 = HandScorer.createScorer(Pairs::new);
        ArrayList<Card> hand0 = CardParser.parseHand("1H2D3S4C5C");
        assertThat(scorer0.score(hand0), is(0));
        Pairs scorer1 = HandScorer.createScorer(Pairs::new);
        ArrayList<Card> hand1 = CardParser.parseHand("7H6C7D9D8C");
        assertThat(scorer1.score(hand1), is(2));
        Pairs scorer2 = HandScorer.createScorer(Pairs::new);
        ArrayList<Card> hand2 = CardParser.parseHand("QHQC8C9SQS");
        assertThat(scorer2.score(hand2), is(6));
        Pairs scorer3 = HandScorer.createScorer(Pairs::new);
        ArrayList<Card> hand3 = CardParser.parseHand("3S3H3D3DJC");
        assertThat(scorer3.score(hand3), is(12));
        Pairs scorer4 = HandScorer.createScorer(Pairs::new);
        ArrayList<Card> hand4 = CardParser.parseHand("AHAC9D9D8C");
        assertThat(scorer4.score(hand4), is(4));
        Pairs scorer5 = HandScorer.createScorer(Pairs::new);
        ArrayList<Card> hand5 = CardParser.parseHand("7H6C7D6D6C");
        assertThat(scorer5.score(hand5), is(8));
    }

    @Test
    void checkFlush() {
        Flush scorer0 = HandScorer.createScorer(Flush::new);
        ArrayList<Card> hand0 = CardParser.parseHand("1H2H3H4H5H");
        assertThat(scorer0.score(hand0), is(5));
        Flush scorer1 = HandScorer.createScorer(Flush::new);
        ArrayList<Card> hand1 = CardParser.parseHand("3S3HJC3DKC");
        assertThat(scorer1.score(hand1), is(1));
        Flush scorer2 = HandScorer.createScorer(Flush::new);
        ArrayList<Card> hand2 = CardParser.parseHand("3C3CJC3CKC");
        assertThat(scorer2.score(hand2), is(6));
    }

    @Test
    void checkFifteenTwos() {
        FifteenTwos scorer0 = HandScorer.createScorer(FifteenTwos::new);
        ArrayList<Card> hand0 = CardParser.parseHand("1H2D3S4C5C");
        assertThat(scorer0.score(hand0), is(2));
        FifteenTwos scorer1 = HandScorer.createScorer(FifteenTwos::new);
        ArrayList<Card> hand1 = CardParser.parseHand("7H6C7D9D8C");
        assertThat(scorer1.score(hand1), is(6));
        FifteenTwos scorer2 = HandScorer.createScorer(FifteenTwos::new);
        ArrayList<Card> hand2 = CardParser.parseHand("AHQC5D8D5C");
        assertThat(scorer2.score(hand2), is(4));
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
