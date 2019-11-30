package cribbage;

import java.util.*;

public class HandScorer {
    private ArrayList<Card> hand;

    public HandScorer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int scoreHand() {
        return scoreRuns() + scorePairs() + scoreFlush() + scoreFifteenTwos();
    }

    public int scoreRuns() {
        Runs scorer = new Runs();
        return scorer.score(this.hand);
    }

    public int scorePairs() {
        Pairs scorer = new Pairs();
        return scorer.score(this.hand);
    }

    public int scoreFlush() {
        Flush scorer = new Flush();
        return scorer.score(this.hand);
    }

    public int scoreFifteenTwos() {
        FifteenTwos scorer = new FifteenTwos();
        return scorer.score(this.hand);
    }
}
