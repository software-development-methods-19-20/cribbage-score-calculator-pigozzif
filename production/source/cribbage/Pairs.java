package cribbage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Pairs {
    private HashMap<Long, Integer> rules;

    public Pairs() {
        this.rules = new HashMap<>();
        rules.put((long) 2, 2);
        rules.put((long) 3, 6);
        rules.put((long) 4, 12);
    }

    public int score(ArrayList<Card> hand) {
        return hand.stream().collect(Collectors.groupingBy(Card::rank, Collectors.counting())).values().stream().
                filter(x -> x > 1).mapToInt(this.rules::get).reduce(0, Integer::sum);
    }
}
