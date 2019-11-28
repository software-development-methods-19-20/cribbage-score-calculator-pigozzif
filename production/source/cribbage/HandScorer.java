package cribbage;

import java.util.ArrayList;

public class HandScorer {
    private ArrayList<Card> hand;

    public HandScorer(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int scoreHand() {
        return checkRuns();
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

    public int checkPairs() {
        return -1;
    }
}
