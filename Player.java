import java.util.ArrayList;

public class Player extends Person {
	
	private String name; //player name
	private int chips;   //player �w�X
	private int bet;     //player �b�����U�`���w�X
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
	
//	public void setOneRoundCard(ArrayList<Card>cards)  //�o�쪺�P��setter
//	{
//		oneRoundCard=cards;
//	}
	
	public boolean hit_me(Table tbl)  //�n�P or not
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
//	public int getTotalValue() //�^�Ǧ��P���ұo���P�I�ƥ[�`
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
	
	public int getCurrentChips() //���a���w�X
	{
		return chips;
	}
	
	public void increaseChips(int diff)  //���a���w�X�ܰ�
	{
		chips-=diff;
	}
	
	public void sayHello()  //���asay hello
	{
		System.out.println("Hello,I am " + name + " .");
		System.out.println("I have " + chips + " chips.");
	}
	

}
