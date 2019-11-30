package cribbage;

import java.util.ArrayList;

public interface Scorer {
    int score(ArrayList<Card> hand);
}
