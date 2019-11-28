package cribbage;

public enum Suite {
    HEART, SPADE, DIAMOND, CLUB;

    private String firstLetter;

    static {
        HEART.firstLetter = "H";
        SPADE.firstLetter = "S";
        DIAMOND.firstLetter = "D";
        CLUB.firstLetter = "C";
    }

    @Override
    public String toString() {
        return this.firstLetter;
    }
}
