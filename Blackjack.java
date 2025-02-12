import java.util.Scanner;
/**
 * GraceDice
 * March 29th, 2023
 */
public class Blackjack
{
    // this is the main class where you can run and play the game
    // if the bankroll goes o zero, you are unable to play anymore
    static final int HEARTS = 0;
    static final int DIAMONDS = 1;
    static final int SPADES = 2;
    static final int CLUBS = 3;

    static final int JACK = 11;
    static final int QUEEN = 12;
    static final int KING = 13;
    static final int ACE = 14;
    static final int BANKROLL = 100;
    public static void main (String [] args)
    {
        Scanner scanner = new Scanner(System.in);
        double bankroll = BANKROLL;
        System.out.println("Starting bankroll: " + bankroll);
        Game game = new Game();

        System.out.println("Welcome to Blackjack!");
        System.out.println("Rules :");
        System.out.println("Your bankroll may not go into negative numbers");
        System.out.println("You may double down if you get 9,10,11 value from your first 2 cards");
        System.out.println("If the dealer shows an ace, you may get insurance");
        System.out.println("If you have two of the same numbers, you may split your hand ");
        System.out.println("There is no five card charlie");
        System.out.println();
        
        while(bankroll > 0)
        {
            System.out.println("Would you like to play? Y/N ");
            String playAgain = scanner.nextLine().toLowerCase();
            
            if (playAgain.equals("y"))
            {
                bankroll = game.playRound(bankroll);
            }
            else if (playAgain.equals("n"))
            {
                break;
            }
            else
            {
                System.out.println("Sorry, wrong input. Try again. ");
            }
            
        }
    }
}
