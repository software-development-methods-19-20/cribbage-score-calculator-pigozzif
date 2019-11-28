package cribbage;

public class Card {
    private String rank;
    private Suite suite;

    public Card(String rank, String suite) {
        this.rank = rank;
        if (suite.equals("H")) {
            this.suite = Suite.HEART;
        }
        else if (suite.equals("D")) {
            this.suite = Suite.DIAMOND;
        }
        else if (suite.equals("S")) {
            this.suite = Suite.SPADE;
        }
        else {
            this.suite = Suite.CLUB;
        }
    }

    public String rank() {
        return this.rank;
    }

    public Suite suite() {
        return this.suite;
    }

    @Override
    public String toString() {
        return this.rank + this.suite.toString().substring(0, 1);
    }
}
