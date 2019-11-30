package cribbage;

import java.util.ArrayList;

public class Flush implements Scorer {

    public Flush() {}

    @Override
    public int score(ArrayList<Card> hand) {
        int flushTotal = 1;
        int jackTotal = 0;
        Suite starterSuite = hand.get(hand.size() - 1).suite();
        Suite precSuite = hand.get(0).suite();
        for (int i=1; i < hand.size(); ++i) {
            Card c = hand.get(i);
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
}
