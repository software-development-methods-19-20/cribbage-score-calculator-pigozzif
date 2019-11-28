package cribbage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        HashMap<Long, Integer> rules = new HashMap<>();
        rules.put((long) 2, 2);
        rules.put((long) 3, 6);
        rules.put((long) 4, 12);
        Map<String, Long> occurences = this.hand.stream().collect(Collectors.groupingBy(Card::rank, Collectors.counting()));
        return occurences.values().stream().filter(x -> x > 1).mapToInt(rules::get).reduce(0, Integer::sum);
    }
}
