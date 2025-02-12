import java.util.ArrayList;
import java.util.Collections;

/**
 * Grace Dice
 * March 29th, 2023
 */
public class Deck 
{
    // This is a class representing a deck of playing cards
    // It includes constants for the four suits and the card ranks
    // The class has an ArrayList of cards as an instance variable
    // It also includes methods to print the deck, deal a card from the top of the deck, get the list of cards in the deck, and shuffle the deck

    static final int HEARTS = 0;
    static final int DIAMONDS = 1;
    static final int SPADES = 2;
    static final int CLUBS = 3;

    static final int JACK = 11;
    static final int QUEEN = 12;
    static final int KING = 13;
    static final int ACE = 14;

    private ArrayList<Card> deck;

    public Deck() 
    {
        deck = new ArrayList<Card>();
        initialize();
    }

    private void initialize() 
    {
        for (int rank = 2; rank <= ACE; rank++) 
        {
            for (int suit = HEARTS; suit <= CLUBS; suit++) 
            {
                Card card = new Card(rank, suit);
                deck.add(card);
            }
        }
        shuffle();
    }

    public void print() 
    {
        for (Card card : deck) 
        {
            System.out.println(card);
        }
    }

    public Card deal() 
    {
        if (deck.isEmpty()) 
        {
            refill();
        }
        return deck.remove(0);
    }

    public ArrayList<Card> getCards() 
    {
        return deck;
    }

    public void shuffle() 
    {
        Collections.shuffle(deck);
        //i just found a better way to shuffle 
    }

    public void refill() 
    {
        initialize();
    }
    
    public boolean isEmpty()
    {
        if (deck.size() == 0)
        {
            return true;
        }
        return false;
    }
}