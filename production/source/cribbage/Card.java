package cribbage;

public class Card {
    private String rank;
    private Suite suite;

    public Card(String rank, String suite) {
        this.rank = rank;
        if (suite.equals("H")) {
            this.suite = Suite.HEART;
        }
    }

    public String rank() {
        return this.rank;
    }

    public Suite suite() {
        return this.suite;
    }
}
