package cribbage;

import java.util.ArrayList;

public class Runs {

    public Runs() {}

    public int score(ArrayList<Card> hand) {
        int bestTotal = 0;
        int currTotal = 0;
        int precValue = hand.get(0).getValue();
        for (int i=1; i < hand.size(); ++i) {
            int currValue = hand.get(i).getValue();
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
}
