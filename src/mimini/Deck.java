package mimini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @coauthor: Hermes Mimini
 * @coauthor: Isaiah DeBenedetto
 * @version: 1.0
 * @date: N/A
 * @Statment: This is my own work.
 * @SIDENOTE: Worked with Isaiah DeBenedetto
 */
public class Deck {

    private final ArrayList<Card> cardArr
            = new ArrayList<>();
    public static String[] color = {"R", "G", "B", "Y"};
    Random rnd = new Random();

    /**
     * Deck constructor with populating for loops.
     *
     */
    public Deck() {

        //initializing
        for (String color1 : color) {
            Card c = new Card(color1, "0");
            cardArr.add(c);
            for (int j = 1; j <= 9; j++) {
                cardArr.add(new Card(color1, j + ""));
                cardArr.add(new Card(color1, j + ""));
            }
            for (int j = 1; j <= 2; j++) {
                cardArr.add(new Card(color1, "S"));
                cardArr.add(new Card(color1, "R"));
                cardArr.add(new Card(color1, "+2"));
            }
        }
        int index = rnd.nextInt(color.length);
        String wildCard = color[index];
        cardArr.add(new Card(wildCard, "WC"));
        cardArr.add(new Card(wildCard, "+4"));

        int index1 = rnd.nextInt(color.length);
        String wildCard1 = color[index1];
        cardArr.add(new Card(wildCard1, "WC"));
        cardArr.add(new Card(wildCard1, "+4"));

        int index2 = rnd.nextInt(color.length);
        String wildCard2 = color[index2];
        cardArr.add(new Card(wildCard2, "WC"));
        cardArr.add(new Card(wildCard2, "+4"));

        int index3 = rnd.nextInt(color.length);
        String wildCard3 = color[index3];
        cardArr.add(new Card(wildCard3, "WC"));
        cardArr.add(new Card(wildCard3, "+4"));
    }

    /**
     * Returns the count of the deck.
     *
     * @return Integer
     */
    public int getCount() {
        return cardArr.size();
    }

    /**
     * This method will shuffle the deck and send the discard pile a card to
     * start the game with.
     *
     * @param dp Discard
     * @param d Deck
     */
    public void setUpDiscardPile(Discard dp, Deck d) {
        d.shuffle();
        dp.addCard(d.cardArr.get(cardArr.size() - 1));
    }

    /**
     * This method will add cards to deck.
     *
     * (Used in discard pile class)
     *
     * @param c Card
     */
    public void getCard(Card c) {
        cardArr.add(c);
    }

    /**
     * This method will deal a card and remove that card from the deck.
     *
     * @return Card
     */
    public Card dealCard() {
        Card c = cardArr.get(0);
        cardArr.remove(c);
        return c;
    }

    /**
     * Returns string description of the deck.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (Card card : cardArr) {
            result.append(card).append(" ");
            count++;
            if (count % 15 == 0) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Shuffles the deck if necessary again.
     *
     * (Not used much since deck is shuffled in set up discard pile method)
     */
    public void shuffle() {
        Collections.shuffle(cardArr);
    }

}
