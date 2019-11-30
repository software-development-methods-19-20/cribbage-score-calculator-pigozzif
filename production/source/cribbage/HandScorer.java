package cribbage;

import java.util.*;
import java.util.function.Supplier;

public class HandScorer {
    private ArrayList<Card> hand;

    public HandScorer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int scoreHand() {
        return createScorer(Runs::new).score(this.hand) + createScorer(Pairs::new).score(this.hand)
                + createScorer(Flush::new).score(this.hand) + createScorer(FifteenTwos::new).score(this.hand);
    }

    public static <T extends Scorer> T createScorer(Supplier<? extends T> ctor) {
        return ctor.get();
    }
}
