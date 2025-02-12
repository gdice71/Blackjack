
/**
 * 
 * GraceDice
 * March 29th, 2023
 * 
 */
public class Card
{
    // This is a class representing a standard playing card
    // It includes constants for the four suits and the card ranks
    // The class has instance variables for the rank, suit, and value of a card
    // It also includes methods to get the rank, suit, value, and string representation of a card
    // The getValue() method calculates the point value of the card in a game of blackjack
    // The toString() method returns a string representation of the card, combining its rank and suit
    
    static final int HEARTS = 0;
    static final int DIAMONDS = 1;
    static final int SPADES = 2;
    static final int CLUBS = 3;

    static final int JACK = 11;
    static final int QUEEN = 12;
    static final int KING = 13;
    static final int ACE = 14;

    //instance veriables
    private int rank;
    private int suit;
    private int value;

    private String [] suits = {"H", "D", "S", "C"};
    private String [] ranks = {"", "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    
    public Card(int gRank, int gSuit)
    {
        rank = gRank;
        suit = gSuit;
    }

    public int getRank ()
    {
        return rank;
    }

    public int getSuit ()
    {
        return suit;
    }

    public String rankToString (int gRank)
    {
        return ranks[gRank];
    }

    public String suitToString (int gSuit)
    {
        return suits[gSuit];
    }

    public String getRankString ()
    {
        return rankToString(rank);
    }

    public String getSuitString ()
    {
        return suitToString(suit);
    }

    public int getValue()
    {
        value = rank;
        if (rank > 10)
        {
            value = 10;
        }
        if (rank == ACE)
        {
            value = 11; //special cases when it goes to 1 
        }
        return value;
    }

    public String toString()
    {
        //get string for the rank and for the suit
        String suitString = suits[suit];
        String rankString = ranks[rank];
        return rankString + suitString;
    }

}
