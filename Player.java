import java.util.ArrayList;

public class Player extends Person {
	
	private String name; //player name
	private int chips;   //player 籌碼
	private int bet;     //player 在此局下注的籌碼
	private ArrayList<Card> oneRoundCard;
	
	public Player(String name,int chips)
	{
		this.name=name;
		this.chips=chips;
	}
	
	public String getName()
	{
		return name;
	}
	
	/**
	 * make bet when player still have chips
	 * @return bet
	 */
	public int makeBet()  
	{
		if(chips>0)
		{
			bet =1;
			return bet;
		}
		else 
			return bet=0;		
	}
	
//	public void setOneRoundCard(ArrayList<Card>cards)  //得到的牌的setter
//	{
//		oneRoundCard=cards;
//	}
	
	public boolean hit_me(Table tbl)  //要牌 or not
	{
		if(getTotalValue()<17)
			return true;
		else
			return false;	
	}
	
	/**
	 * get the data from cards and calculate total rank
	 * Ace could be 1 or 11
	 * @return
	 */
//	public int getTotalValue() //回傳此牌局所得的牌點數加總
//	{
//		int total=0;
//		int rank=0;
//		for(int i=0;i<oneRoundCard.size();i++)
//		{
//			rank=oneRoundCard.get(i).getRank();
//			if(rank==11 || rank==12 || rank==13)
//			{
//				rank=10;
//			}
//			total+=rank;
//			if(total<=21 && rank==1)
//				total+=11;
//		}
//		return total;
//	}
	
	public int getCurrentChips() //玩家的籌碼
	{
		return chips;
	}
	
	public void increaseChips(int diff)  //玩家的籌碼變動
	{
		chips-=diff;
	}
	
	public void sayHello()  //玩家say hello
	{
		System.out.println("Hello,I am " + name + " .");
		System.out.println("I have " + chips + " chips.");
	}
	

}
