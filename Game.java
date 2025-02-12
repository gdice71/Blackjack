import java.util.Scanner;
/**
 * 
 * Grace Dice
 * March 30th, 2023
 * 
 */
public class Game
{
    //This class defines a game of blackjack, and has several constants, such as the suits and values of cards, as well as the initial bankroll
    //The class uses a Scanner to take user input for the player's moves, and has private methods for the player's turn, the dealer's turn, and determining the winner of a round
    //The public method, playRound(), is the main method that runs a round of blackjack. It starts by taking input from the user for their bet, and then shuffles and deals the cards. The method then handles any insurance bets if the dealer has an ace, and then plays out the hands of the dealer and player, before determining the winner and adjusting the player's bankroll accordingly

    static final int HEARTS = 0;
    static final int DIAMONDS = 1;
    static final int SPADES = 2;
    static final int CLUBS = 3;

    static final int JACK = 11;
    static final int QUEEN = 12;
    static final int KING = 13;
    static final int ACE = 14;
    static final int BANKROLL = 100;

    Scanner scanner = new Scanner(System.in);
    Deck deck;
    public Game() 
    {
        deck = new Deck();
    }


    // This is a private method that returns a String value for the player's move in a game.
    //return move
    private String getPlayerMove()
    {
        while (true)
        {
            System.out.print("Enter move (hit/stand): ");
            String move = scanner.nextLine().toLowerCase();

            if (move.equals("hit") || move.equals("stand"))
            {
                return move;
            }
            System.out.println("Please try again."); 
        }
    }

    // play turn - hit or stand
    // This is a private method that handles the player's turn in a game of blackjack.
    // It takes the player's and deck as parameters.
    // return true or false
    private boolean playerTurn(Hand player, Deck deck)
    {
        // hit or stand
        // if the player hits then add card and print out hand
        // figure out if busted

        // The player can either hit or stand during their turn.
        // If the player hits, a card is added to their hand and their hand is printed out.
        // The method will continue to prompt the player to hit or stand until they stand or bust.
        while (true)
        {
            String move = getPlayerMove();
            if (move.equals("hit"))
            {
                Card card = deck.deal();
                player.addCard(card);
                System.out.println("Your card was: " + card);
                System.out.println("Player's hand");
                System.out.println(player);
                if (player.busted())
                {
                    return true;
                }
            }
            else
            {
                return false; 
            }
        }
        //return of false means busted
    }

    // This is a private method that handles the dealer's turn in a game of blackjack.
    // It takes the dealer's hands and the deck as parameters.
    // return tru of false
    private boolean dealerTurn(Hand dealer, Deck deck)
    {
        // The dealer's hand is printed out along with its current value.
        // The dealer will hit until their hand value is greater than or equal to 17.
        while(true)
        {
            System.out.println("Dealer's hand");
            System.out.println(dealer);

            int value = dealer.getValue();
            System.out.println("Dealer's hand has a value " + value);

            System.out.println("Press enter to continue.");
            String move = scanner.nextLine().toLowerCase();

            if (value < 17)
            {
                System.out.println("Dealer's hits");
                Card card = deck.deal();
                dealer.addCard(card);
                System.out.println("Dealer card was " + card);
                if (dealer.busted())
                {
                    System.out.println("Dealer busted.");
                    return false;
                }
            }
            else
            {
                System.out.println("Dealer stands.");
                return false;
            }
        }
    }
    // This is a private method that determines whether the player has won the game of blackjack.
    // It takes the player's and dealer's hands as parameters.
    private boolean playerWins(Hand player, Hand dealer)
    {
        if (player.busted())
        {
            return false;
        }
        if (dealer.busted())
        {
            return true;
        }
        return player.getValue() > dealer.getValue();
    }

    // This is a private method that determines whether the game results in a push, i.e., a tie between the player and dealer.
    // It takes the player's and dealer's hands as parameters.
    private boolean push(Hand player, Hand dealer)
    {
        return player.getValue() == dealer.getValue() || player.getValue() > 21 && dealer.getValue() > 21 ;
    }

    // This is a private method that determines the winner of the game and returns the corresponding payout.
    // It takes the dealer's and player's hands, as well as the bet amount, as parameters.
    // Returns bet 
    private double findWinner(Hand dealer, Hand player, int bet)
    {
        // check if player wins
        // check if there was push
        // if neither, dealer wins
        if (player.hasBlackjack())
        {
            return 1.5 * bet;
        }
        if (playerWins(player, dealer))
        {
            System.out.println("Player wins.");
            return bet;
        }
        else if (push(player, dealer))
        {
            System.out.println("You push.");
            return 0;
        }
        else
        {
            System.out.println("Dealer wins.");
            return -bet;
        }

    }
    // returns bankroll
    // parameter = bankroll
    // just plays the game
    
    // Add a refillDeck method to handle deck refilling
    public void refillDeck() 
    {
        deck.refill();
        deck.shuffle();
        System.out.println("Deck has been refilled and shuffled.");
    }

    public double playRound(double bankroll) 
    {
        System.out.println("What is your bet? ");
        int bet = scanner.nextInt();
        while (bet > bankroll || bet < 0)
        {
            System.out.println("Choose a bet that is within your bank value");
            bet = scanner.nextInt();
        }

        int funcounter = 0;
        // Check if the deck is empty before dealing
        if (deck.isEmpty())
        {
            refillDeck();
        }
        
        

        Hand player = new Hand();
        Hand dealer = new Hand();

        Card card1 = deck.deal();
        Card card2 = deck.deal();
        player.addCard(card1);
        player.addCard(card2);

        Card dcard1 = deck.deal();
        Card dcard2 = deck.deal();
        dealer.addCard(dcard1);
        dealer.addCard(dcard2);

        System.out.println("Player's Hand");
        System.out.println(player);

        System.out.println("Dealer's Hand");
        dealer.printDealerHand();

        // check if dealer has Ace
        if (dcard1.getRank() == 14) 
        {
            System.out.print("Dealer has an Ace. Would you like insurance? Y/N: ");
            String answer = scanner.next().toLowerCase();
            if (answer.equals("y") && dealer.hasBlackjack()) 
            {
                System.out.println("Dealer has blackjack. Insurance pays 2 to 1.");
                bankroll += bet;
                System.out.println("New bankroll: " + bankroll);
            }
            if (answer.equals("y") && !dealer.hasBlackjack())
            {
                System.out.println("Dealer doesn't have blackjack.");
                bankroll += 0.5 * -bet;
                System.out.println("New bankroll: " + bankroll);
            }
            if (answer.equals("n") && dealer.hasBlackjack()) 
            {
                System.out.println("Dealer has blackjack.");
                bankroll += 2 * -bet;
                System.out.println("New bankroll: " + bankroll);
            }
            if (answer.equals("n") && !dealer.hasBlackjack())
            {
                System.out.println("Dealer doesn't have blackjack.");
                playHand(player, dealer, deck, bet, bankroll);
            }
        } 

        // Check for double down option
        if ((card1.getRank() + card2.getRank()) <= 11 && (card1.getRank() + card2.getRank()) >= 9)
        {
            System.out.println("Do you want to double down? Y/N");
            String answer = scanner.next().toLowerCase();
            if (answer.equals("y")) 
            {
                bet = bet*2;
                Card card3 = deck.deal();
                player.addCard(card3);                
                System.out.println("Player's Hand after double down:");
                System.out.println(player);
                funcounter++;
                if ((card3.getRank() + card2.getRank() + card1.getRank()) < 21) 
                {
                    Game game = new Game();
                    System.out.println("Enter for dealer turn.");
                    String move = scanner.nextLine().toLowerCase();

                    game.dealerTurn(dealer, deck);

                    double bankrollChange = game.findWinner(dealer, player, bet);
                    bankroll += bankrollChange;
                    System.out.println("New bankroll: " + bankroll);
                } 
                else 
                {

                    System.out.println("You busted.");
                }
            }
        }

        //check for split
        if (card1.getRank() == card2.getRank()) 
        {
            System.out.println("Do you want to split your hand? Y/N ");
            String split = scanner.next().toLowerCase();
            if (split.equals("y")) 
            {
                // create two new hands and deal one card to each
                Hand splitHand1 = new Hand();
                splitHand1.addCard(card1);

                Hand splitHand2 = new Hand();
                splitHand2.addCard(card2);

                // play each hand separately
                System.out.println("Playing hand 1:");
                bankroll = playHand(splitHand1, dealer, deck, bet, bankroll);
                System.out.println("Playing hand 2:");
                bankroll = playHand(splitHand2, dealer, deck, bet, bankroll);

                return bankroll;
            }
        }

        // play single hand
        if(funcounter > 0)
        {
            return bankroll;
        }
        else
        {
            bankroll = playHand(player, dealer, deck, bet, bankroll);
            return bankroll;
        }
    }

    // This method plays a hand of Blackjack for the player, consisting of the player's turn, followed by the dealer's turn.
    // If the player busts, their turn ends and the method returns. If the player does not bust, the dealer takes their turn
    // and the method calculates the change in the player's bankroll based on the outcome of the hand.
    // parameter player the Hand object representing the player's hand
    // parameter dealer the Hand object representing the dealer's hand
    // parameter deck the Deck object representing the deck of cards used in the game
    // parameter bet the amount of money the player has bet on this hand
    // parameter bankroll the player's current bankroll
    //return the updated bankroll after the hand has been played
    public double playHand(Hand player, Hand dealer, Deck deck, int bet, double bankroll) 
    {
        Game game = new Game();
        boolean playerBusted = game.playerTurn(player, deck);

        if (playerBusted) 
        {
            System.out.println("You busted.");
            bankroll -= bet;
            System.out.println("New bankroll: " + bankroll);
        } 
        else 
        {
            System.out.println("Enter for dealer turn.");
            String move = scanner.nextLine().toLowerCase();

            game.dealerTurn(dealer, deck);

            double bankrollChange = game.findWinner(dealer, player, bet);
            bankroll += bankrollChange;
            System.out.println("New bankroll: " + bankroll);
        }
        return bankroll;
    }

}