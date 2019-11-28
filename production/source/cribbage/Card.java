package cribbage;

public class Card {
    private String rank;
    private Suite suite;
    private int value;

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
        try {
            this.value = Integer.parseInt(this.rank);
        }
        catch (NumberFormatException e){
            if (this.rank.equals("A")) {
                this.value = 1;
            } else {
                this.value = 10;
            }
        }
    }

    public String rank() {
        return this.rank;
    }

    public int getValue() {
        return this.value;
    }

    public Suite suite() {
        return this.suite;
    }

    @Override
    public String toString() {
        return this.rank + this.suite.toString();
    }
}
