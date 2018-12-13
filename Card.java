

public class Card {
	private Suit suit; // Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; // 1~13
	public enum Suit {Spade, Heart, Diamond, Club};

	Suit thisCardSuit = Suit.Club;

	
	public Card(Suit s, int value) 
	{
		suit = s;
		rank = value;
	}

	/**
	 * change rank of card into letter
	 */
	public void printCard() 
	{
		String rank = "";
		switch (getRank()) 
		{
		case 1:
			rank = "Ace";
			break;
		case 2:
			rank = "Two";
			break;
		case 3:
			rank = "Three";
			break;
		case 4:
			rank = "Four";
			break;
		case 5:
			rank = "Five";
			break;
		case 6:
			rank = "Six";
			break;
		case 7:
			rank = "Seven";
			break;
		case 8:
			rank = "Eight";
			break;
		case 9:
			rank = "Nine";
			break;
		case 10:
			rank = "Ten";
			break;
		case 11:
			rank = "Jack";
			break;
		case 12:
			rank = "Queen";
			break;
		case 13:
			rank = "King";
			break;
		
		}
		
		
		System.out.println(getSuit() + "," + rank + "\n");
	}

	public Suit getSuit() 
	{
		return suit;
	}

	public int getRank() 
	{
		return rank;
	}



}
