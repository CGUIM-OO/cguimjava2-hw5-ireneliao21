import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;     //一疊牌
	private ArrayList<Card> usedCard;  //抽出的牌
	private ArrayList<Card> openCard;  //打開的牌
	public int nUsed;                  //抽出的牌的數量
	
    /**
     * s = the suit of card , z = the value of card
     * put c1(s,z) into array list : cards
     * @param nDeck
     */
	
	public Deck(int nDeck) 
	{
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		for (int i = 0; i < nDeck; i++) 
		{
			for (Card.Suit s : Card.Suit.values()) 
			{
				for (int z = 1; z <= 13; z++) 
				{
					Card c1 = new Card(s, z);
					cards.add(c1);
				}
			}
		}
		shuffle();
	}
	

	/**
	 * shuffle cards
	 */
	
	public void shuffle() 
	{
		cards.addAll(usedCard);
		Random rnd = new Random();
		
		for (int i = 0; i < 52; i++) 
		{
			int j = rnd.nextInt(i + 1);//隨機產生52個整數變數
			cards.add(cards.get(j));   
			cards.remove(cards.get(j));
		}
		usedCard.clear();
		nUsed = 0;
	}

	/**
	 * shuffle when nUsed have 52 cards
	 * add the taken out card into usedCard and openCard 
	 * firstcard = the first card take out from cards
	 * @return the taken out card
	 */
	
	public Card getOneCard(boolean isOpened) 
	{
		openCard = new ArrayList<Card>();
		if(nUsed==52)
		{
			shuffle();
			nUsed=0;
			return cards.get(0);
		}
		else
		{
			if(isOpened=true)
			{
				Card firstcard = cards.get(0);
				openCard.add(firstcard);
				cards.remove(0);
				usedCard.add(firstcard);
				nUsed++;
			}	
			return cards.get(0);
		}
	}
	
	public ArrayList<Card>getopenedCard()  //儲存攤開的牌
	{
		return openCard;
	}

	public void printDeck() 
	{

		for (int i = 0; i < cards.size(); i++) 
		{
			Card c1 = cards.get(i);
			c1.printCard();
		}

	}

	public ArrayList<Card> getAllCards() 
	{
		return cards;
	}


}
