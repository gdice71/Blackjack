import java.util.*;
/**
 * 
 * Grace Dice
 * March 29th, 2023
 * 
 */
public class Hand
{
    // This is a class representing a hand of playing cards.
    // It includes constants for the four suits and the card ranks.
    // The class has an ArrayList of cards as an instance variable.
    // It also includes methods to add a card to the hand and calculate the value of the hand in a game of blackjack,
    // check if the hand has blackjack or is busted, print the dealer's hand (showing only one card), and get a string representation of the hand.
    
    static final int HEARTS = 0;
    static final int DIAMONDS = 1;
    static final int SPADES = 2;
    static final int CLUBS = 3;

    static final int JACK = 11;
    static final int QUEEN = 12;
    static final int KING = 13;
    static final int ACE = 14;

    private ArrayList<Card> cards;

    public Hand()
    {
        cards = new ArrayList<Card>(); 
    }

    public void addCard(Card gCard)
    {
        cards.add(gCard);
    }

    public int getValue()
    {
        int sum = 0;
        int aces = 0;
        for (Card card : cards)
        {
            sum += card.getValue();
            if (card.getRank() == ACE)
            {
                aces++;
            }
        }
        while (aces > 0 && sum > 21)
        {
            sum -= 10;
            aces--;
        }
        return sum;
    }

    public boolean hasBlackjack()
    {
        return getValue() == 21 && cards.size() == 2;
    }

    public boolean busted()
    {
        return getValue() > 21;
    }

    //print out only one dealer card, show X instead
    public void printDealerHand()
    {
        for (int i = 0; i < cards.size(); i++)
        {
            Card card = cards.get(i);
            if (i == 1)
            {
                System.out.print("X ");
            }
            else 
            {
                System.out.print(card + " ");
            }
        }
        System.out.println();
    }

    
    public String toString()
    {
        String result = "";
        for (Card card: cards)
        {
            result += card + " ";
        }
        result += "(" + getValue() + ")";
        return result;
    }

}
