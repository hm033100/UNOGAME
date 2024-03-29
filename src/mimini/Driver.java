package mimini;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @coauthor: Hermes Mimini
 * @coauthor: Isaiah DeBenedett
 * @version: 1.0
 * @date: N/A
 * @Statment: This is my own work.
 * @SIDENOTE: Worked with Isaiah DeBenedetto
 */
public class Driver {

    static Deck d = new Deck();
    static Discard dp = new Discard();
    static int nextPlayer = 0;
    static int direction = 1;
    static int totalNumberPlayers = 5;
    static boolean effectApplied = true;
    static String winnerDisplay = """
            \\\\        //\\\\        //  ||  ||\\\\     || ||\\\\     || ||===  ||==||
             \\\\      //  \\\\      //   ||  ||  \\\\   || ||  \\\\   || ||     ||  ||
              \\\\    //    \\\\    //    ||  ||   \\\\  || ||   \\\\  || ||==   ||=||
               \\\\  //      \\\\  //     ||  ||    \\\\ || ||    \\\\ || ||     ||  \\\\
                \\\\//        \\\\//      ||  ||     \\\\|| ||     \\\\|| ||===  ||   \\\\
            """;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creating a deck and shuffeling it
        d.setUpDiscardPile(dp, d);
        Card topCard = dp.topCard();

        ArrayList<PlayerHand> players = new ArrayList<>();

        //Creating two player hands
        PlayerHand player1 = new PlayerHand("Player 1");
        PlayerHand player2 = new PlayerHand("Player 2");
        PlayerHand player3 = new PlayerHand("Player 3");
        PlayerHand player4 = new PlayerHand("Player 4");
        PlayerHand player5 = new PlayerHand("Player 5");

        //Populating player hands 
        for (int i = 0; i < 7; i++) {
            player1.pickUpCard(d.dealCard());
            player2.pickUpCard(d.dealCard());
            player3.pickUpCard(d.dealCard());
            player4.pickUpCard(d.dealCard());
            player5.pickUpCard(d.dealCard());
        }

        //populating the arraylist with playerhands
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);

        //Setting up the first information for the user
        System.out.println("WELCOME TO THE UNO GAME! HAVE FUN!");
        System.out.println("Top Card is: " + topCard);
        System.out.println(player1 + "\n" + player2 + "\n" + player3 + "\n"
                + player4);
        System.out.println("----------------------------------------------\n");

        /*
        checking if the players are winners (have no cards)
        checking if the deck is empty (if so repopulating it with the cards
        from the discard pile)
        */
        while (!player1.isWinner() && !player2.isWinner() && !player3.isWinner() && !player4.isWinner()) {

            //display players with uno
            for (PlayerHand player : players) {
                if (player.isUno()) {
                    System.out.println(player.getName() + " is UNO!!!");
                    System.out.println("----------------------------------------------\n");
                }
            }

            playerTurn(players.get(nextPlayer));
            int nextNextPlayer = nextPlayer + direction + totalNumberPlayers;
            nextNextPlayer %= totalNumberPlayers;
            nextPlayer(players.get(nextPlayer), players.get(nextNextPlayer));
        }

        //display winner 
        for (PlayerHand player : players) {
            if (player.isWinner()) {
                System.out.println("-----------------------------------------------------------------\n");
                System.out.println(winnerDisplay);
                System.out.println("-----------------------------------------------------------------\n");
                System.out.println(player.getName() + " is the winner!!!");
                System.out.println("----------------------------------------------\n");
            }
        }

    }

    /**
     * This method simulates a turn for a normal uno game.
     *
     * @param p is player hand
     */
    public static void playerTurn(PlayerHand p) {

        //compare top card with player hand
        if (p.hasMatch(dp.topCard())) {

            //if it has a match
            Card cardPlayed = p.playCard(dp.topCard(), dp);
            System.out.println("It is " + p.getName() + " turn!");
            System.out.println(p.getName() + " played " + cardPlayed);
            if (p.getCount() > 0) {
                System.out.println(p);
                System.out.println("Top Card is: " + dp.topCard());
                System.out.println("----------------------------------------------\n");
                effectApplied = false;
            }
        } else {

            //if it doesnt
            System.out.println(p.getName() + " has no match");
            p.pickUpCard(d.dealCard());
            System.out.println(p.getName() + " picked up a " + p.getLastCard());
            System.out.println(p);
            System.out.println("----------------------------------------------\n");
        }

    }

    /**
     * This method determines the order of the game with mathematical logic and
     * in case effectApplied is false it will apply the effect as well.
     *
     * @param p PlayerHand
     * @param pNext PlayerHand
     */
    public static void nextPlayer(PlayerHand p, PlayerHand pNext) {

        //check to see if the topcard is an effect card
        if (!(Objects.equals(dp.topCard().getValue(), "S") || Objects.equals(dp.topCard().getValue(), "R")
                || Objects.equals(dp.topCard().getValue(), "+2") || Objects.equals(dp.topCard().getValue(), "WC")
                || Objects.equals(dp.topCard().getValue(), "+4"))) {

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer +
                    direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;

            //if statement to check if the topcard is a reverse
        } else if (Objects.equals(dp.topCard().getValue(), "R") && !effectApplied) {

            //switch the direction
            direction *= -1;

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer + direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;
            effectApplied = true;

            //if statment to check if the topcard is a skip
        } else if (Objects.equals(dp.topCard().getValue(), "S") && !effectApplied) {

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer + 2 * direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;
            effectApplied = true;

            //if statment to check if the topcard is a +2
        } else if (Objects.equals(dp.topCard().getValue(), "+2") && !effectApplied) {

            //if statment to check if deck count is smaller than 2
            //if so populates the deck with cards from discard pile
            if (d.getCount() < 2) {
                dp.populateDeck(d);
            }

            //next player picks up card two times
            pNext.pickUpCard(d.dealCard());
            System.out.println(pNext.getName() + " picked up a " + pNext.getLastCard());
            pNext.pickUpCard(d.dealCard());
            System.out.println(pNext.getName() + " picked up a " + pNext.getLastCard());
            System.out.println(pNext);
            System.out.println("----------------------------------------------\n");

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer + 2 * direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;
            effectApplied = true;

            //if statment to check if the topcard is a +4
        } else if (Objects.equals(dp.topCard().getValue(), "+4") && !effectApplied) {

            //if statment to check if deck count is smaller than 2
            //if so populates the deck with cards from discard pile
            if (d.getCount() < 4) {
                dp.populateDeck(d);
            }

            System.out.println(p.getName() + " chooses "
                    + dp.topCard().chooseColor() + " as the color!");

            pNext.pickUpCard(d.dealCard());
            System.out.println(pNext.getName() + " picked up a " + pNext.getLastCard());
            pNext.pickUpCard(d.dealCard());
            System.out.println(pNext.getName() + " picked up a " + pNext.getLastCard());
            pNext.pickUpCard(d.dealCard());
            System.out.println(pNext.getName() + " picked up a " + pNext.getLastCard());
            pNext.pickUpCard(d.dealCard());
            System.out.println(pNext.getName() + " picked up a " + pNext.getLastCard());
            System.out.println(pNext);
            System.out.println("----------------------------------------------\n");

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer + 2 * direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;
            effectApplied = true;

            //if statement to check if topcard is a wildcard
        } else if (Objects.equals(dp.topCard().getValue(), "WC")) {

            System.out.println(p.getName() + " chooses "
                    + dp.topCard().chooseColor() + " as the color!");

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer + direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;
            effectApplied = true;

            //if statment to check if card is an effect and if its effect has been applied
        } else if ((Objects.equals(dp.topCard().getValue(), "S") && effectApplied
                || Objects.equals(dp.topCard().getValue(), "R") && effectApplied
                || Objects.equals(dp.topCard().getValue(), "+2") && effectApplied
                || Objects.equals(dp.topCard().getValue(), "+4") && effectApplied)) {

            //math logic to keep the game going in order of the cards
            nextPlayer = nextPlayer + direction + totalNumberPlayers;
            nextPlayer %= totalNumberPlayers;
        }
    }

}
