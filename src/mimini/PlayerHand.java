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
public class PlayerHand {

    private final ArrayList<Card> handArr;
    private final String name;

    /**
     * Constructor for player hand.
     *
     * @param name String
     */
    public PlayerHand(String name) {
        handArr = new ArrayList<>();
        this.name = name;
    }

    /**
     * This method will return the last card of the array.
     *
     * @return Card
     */
    public Card getLastCard() {
        return handArr.get(handArr.size() - 1);
    }

    /**
     * Adds a card that is sent to the array.
     *
     * @param c Card
     */
    public void pickUpCard(Card c) {
        handArr.add(c);
    }

    /**
     * Returns the name of the player hand.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Goes through the array to test if the card sent to it has a match in the
     * hand.
     *
     * @param c Card
     * @return Boolean
     */
    public boolean hasMatch(Card c) {
        for (int i = 0; i < handArr.size(); i++) {
            if (c.isMatch(handArr.get(i))) {
                return true;
            } else if (handArr.get(i).getValue().equals("WC")
                    || handArr.get(i).getValue().equals("+4")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds where the matching card is by looping through the array.
     *
     * @param c Card
     * @param dp Discard
     * @return Card
     */
    public Card playCard(Card c, Discard dp) {
        //find the index of the matching card
        int index = 0;
        for (; index < handArr.size(); index++) {
            if (c.isMatch(handArr.get(index))) {
                break;
            } else if (handArr.get(index).getValue().equals("WC")
                    || handArr.get(index).getValue().equals("+4")) {
                break;
            }
        }
        //remove and return the card at index 'index'
        Card cardToPlay = handArr.get(index);
        dp.addCard(cardToPlay);
        handArr.remove(cardToPlay);
        return cardToPlay;
    }

    /**
     * Returns the count of the player hand.
     *
     * @return Integer
     */
    public int getCount() {
        return handArr.size();
    }

    /**
     * This method will tell if the user is a winner or not.
     *
     * @return Boolean
     */
    public boolean isWinner() {
        boolean isWinner = false;
        if (handArr.isEmpty()) {
            isWinner = true;
        }
        return isWinner;
    }

    /**
     * This method will tell if the user is uno or not.
     *
     * @return Boolean
     */
    public boolean isUno() {
        boolean isUno = false;
        if (handArr.size() == 1) {
            isUno = true;
        }
        return isUno;
    }

    /**
     * String description of the players hand.
     *
     * @return String
     */
    @Override
    public String toString() {
        String hand = "";
        for (int i = 0; i < handArr.size(); i++) {
            hand += handArr.get(i) + " ";
        }
        return name + " = { " + hand + "}";
    }

}
