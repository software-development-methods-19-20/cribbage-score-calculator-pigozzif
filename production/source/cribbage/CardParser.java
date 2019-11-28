package cribbage;

import java.util.ArrayList;

public class CardParser {
    public static Card parseCard(String cardAsText) {
        return new Card(cardAsText.substring(0, 1), cardAsText.substring(1, 2));
    }

    public static ArrayList<Card> parseHand(String handAsText) {
        ArrayList<Card> ans = new ArrayList<>();
        for (int start=0; start <= handAsText.length() - 2; start+=2) {
            ans.add(parseCard(handAsText.substring(start, start + 2)));
        }
        return ans;
    }
}
