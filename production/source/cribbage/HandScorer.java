package cribbage;

import java.util.*;
import java.util.stream.Collectors;

public class HandScorer {
    private ArrayList<Card> hand;

    public HandScorer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int scoreHand() {
        return checkRuns() + scorePairs() + checkFlush() + checkFifteenTwos();
    }

    public int checkRuns() {
        int bestTotal = 0;
        int currTotal = 0;
        int precValue = this.hand.get(0).getValue();
        for (int i=1; i < this.hand.size(); ++i) {
            int currValue = this.hand.get(i).getValue();
            if (currValue - precValue == 1) {
                currTotal += 1;
                bestTotal = Math.max(currTotal, bestTotal);
            }
            else {
                currTotal = 0;
            }
            precValue = currValue;
        }
        if (bestTotal < 2) {
            return 0;
        }
        return bestTotal + 1;
    }

    public int scorePairs() {
        HashMap<Long, Integer> rules = new HashMap<>();
        rules.put((long) 2, 2);
        rules.put((long) 3, 6);
        rules.put((long) 4, 12);
        return this.hand.stream().collect(Collectors.groupingBy(Card::rank, Collectors.counting())).values().stream().
                filter(x -> x > 1).mapToInt(rules::get).reduce(0, Integer::sum);
    }

    public int checkFlush() {
        int flushTotal = 1;
        int jackTotal = 0;
        Suite starterSuite = this.hand.get(this.hand.size() - 1).suite();
        Suite precSuite = this.hand.get(0).suite();
        for (int i=1; i < this.hand.size(); ++i) {
            Card c = this.hand.get(i);
            Suite currSuite = c.suite();
            if (c.rank().equals("J") & starterSuite == currSuite) {
                jackTotal = 1;
            }
            flushTotal = (currSuite == precSuite) ? flushTotal + 1 : 0;
            precSuite = currSuite;
        }
        flushTotal = (flushTotal >= 4) ? 4 : 0;
        if (flushTotal == 4 & precSuite == starterSuite) {
            flushTotal += 1;
        }
        return flushTotal + jackTotal;
    }

    public int checkFifteenTwos() {
        FifteenTwos scorer = new FifteenTwos();
        return scorer.checkFifteenTwos(this.hand);
    }
}
