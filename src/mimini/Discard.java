package mimini;

import java.util.ArrayList;

/**
 * @coauthor: Hermes Mimini
 * @coauthor: Isaiah DeBenedetto
 * @version: 1.0
 * @date: N/A
 * @Statment: This is my own work.
 * @SIDENOTE: Worked with Isaiah DeBenedetto
 */
public class Discard {

    private final ArrayList<Card> cardArr
            = new ArrayList<>();

    /**
     * Constructor for discard pile.
     *
     */
    public Discard() {

    }

    /**
     * This method adds the card sent to the discard array.
     *
     * @param c Card
     */
    public void addCard(Card c) {
        cardArr.add(c);
    }

    /**
     * This method moves all the cards from the discard pile to the deck.
     *
     * @param d Deck
     */
    public void populateDeck(Deck d) {
        for (int i = 0; i < cardArr.size(); i++) {
            d.getCard(cardArr.get(i));
            cardArr.remove(cardArr.get(i));
        }
    }

    /**
     * This method will return the top card of the discard pile.
     *
     * @return Card
     */
    public Card topCard() {
        return new Card(cardArr.get(cardArr.size() - 1).getColor(),
                cardArr.get(cardArr.size() - 1).getValue());
    }

    /**
     * This method returns a string description of the discard pile.
     *
     * (For testing purposes mainly)
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Discard Pile: ");
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

}
