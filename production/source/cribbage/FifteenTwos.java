package cribbage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class FifteenTwos {
    private HashSet<String> duplicates;

    public FifteenTwos() {
        this.duplicates = new HashSet<>();
    }

    public int score(ArrayList<Card> hand) {
        hand.sort(Comparator.comparing(Card::rank).thenComparing(Card::suite));
        return scoreAndGrow(hand);
    }

    private int scoreAndGrow(ArrayList<Card> parent) {
        int n = parent.size();
        int score = 0;
        if (n < 2) {
            return score;
        }
        String id = parent.stream().map(Card::toString).reduce("", String::concat);
        if (this.duplicates.contains(id)) {
            return score;
        }
        else {
            this.duplicates.add(id);
        }
        if (parent.stream().map(Card::getValue).reduce(0, Integer::sum) == 15) {
            score += 2;
        }
        for (int i=0; i < n; ++i) {
            ArrayList<Card> child = new ArrayList<>();
            for (int j=0; j < n; ++j) {
                if (j != i) {
                    child.add(parent.get(j));
                }
            }
            score += scoreAndGrow(child);
        }
        return score;
    }
}
