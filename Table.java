import java.util.ArrayList;

public class Table 
{
	static final int MAXPLAYER = 4; // 最多只能坐四個人
	private Deck allcards;          //所有的牌
	private Player[] allplayers;
	private ArrayList<Card> hostcard = new ArrayList<Card>(); //莊家拿到的牌
	private ArrayList<ArrayList<Card>> pgcard = new ArrayList<ArrayList<Card>>(); //玩家拿到的牌
	private Dealer host; // 莊家
	private int[] pos_betArray = new int[MAXPLAYER]; // 每個玩家在一局下的注
	private int nDeck;

	public Table(int nDeck) 
	{
		this.nDeck = nDeck;
		this.allcards = new Deck(nDeck); //實體化deck
		this.allplayers = new Player[MAXPLAYER]; //實體化player
		pgcard.clear();
		for(int i=0;i<allplayers.length;i++)
		{
			pgcard.add(new ArrayList<Card>());
		}
	}

	public void set_player(int pos, Player p) // 將(位置,player)放入allplayers array
	{
		allplayers[pos] = p;
	}

	public Player[] get_player() // 在桌上的player
	{
		return allplayers;
	}
	
	public int[]get_players_bet() //玩家的下注
	{
		return pos_betArray;
	}
	
	public void set_dealer(Dealer d) // 把莊家放到牌桌上
	{
		this.host = d;
	}

	public Card get_face_up_card_of_dealer()
	{
		return hostcard.get(1);
	}


	private void ask_each_player_about_bets() 
	{
		for (int i = 0; i < allplayers.length; i++) 
		{
			allplayers[i].sayHello();
			allplayers[i].makeBet();
			pos_betArray[i] = allplayers[i].makeBet(); //放入每個玩家在每一局下的賭注
		}

	}

	private void distribute_cards_to_dealer_and_players() 
	{
		for (int i = 0; i < allplayers.length; i++) 
		{
			pgcard.get(i).add(allcards.getOneCard(true)); //從allcards中拿一張排放到pgcard(第一張)
			pgcard.get(i).add(allcards.getOneCard(true)); //從allcards中拿一張排放到pgcard(第二張)
			allplayers[i].setOneRoundCard(pgcard.get(i)); 
			
		}
		hostcard.add(allcards.getOneCard(false));
		hostcard.add(allcards.getOneCard(true));
		host.setOneRoundCard(hostcard);
		System.out.println("Dealer's face up card is");
		get_face_up_card_of_dealer().printCard();
	}
	
	private void ask_each_player_about_hits()
	{
		for (int i = 0; i < allplayers.length; i++) //問每一個玩家
		{
			if(allplayers[i].getTotalValue()<=21) //如果玩家的牌沒有爆
			{
			boolean hit=false;
			System.out.println(allplayers[i].getName()+"'s Cards now:");
			for(Card c : pgcard.get(i)) 
			{
				c.printCard();
			}
			do
			{
				hit=allplayers[i].hit_me(this); //要牌
				if(hit)
				{
					pgcard.get(i).add(allcards.getOneCard(true));
					allplayers[i].setOneRoundCard(pgcard.get(i));
					System.out.println("Hit! "+allplayers[i].getName()+"'s Cards now:");
					for(Card c : pgcard.get(i)) 
					{
						c.printCard();
					}
				}
				else //不要牌
				{
					System.out.println(" Pass hit!");
				}
			}
			while(hit);
			}
			System.out.println(allplayers[i].getName()+"'s hit is over!"); //不要再問要不要牌了
		}
	}
	
	private void ask_dealer_about_hits()
	{
		boolean hit=host.hit_me(this);
		if(hit)
		{
			do
			{
				hit=host.hit_me(this); //要牌
				if(hit)
				{
					hostcard.add(allcards.getOneCard(true));
					host.setOneRoundCard(hostcard);
				}
			}
			while(hit);
			System.out.println("Dealer's hit s over!");//不要再問要不要牌了 
		}
		
    }
	
	private void calculate_chips()
	{
		System.out.print("Dealer's card value is " + host.getTotalValue() + ",Cards:" );
		host.printAllCard();
		for(int i=0;i<allplayers.length;i++)
		{
			System.out.println(allplayers[i].getName()+"card value is "+allplayers[i].getTotalValue());
			if(host.getTotalValue()<=21) //莊家沒爆的情況下
			{
				if(allplayers[i].getTotalValue()<=21) //玩家也沒有爆
				{
					if(host.getTotalValue()>allplayers[i].getTotalValue()) //莊家贏
					{
						allplayers[i].increaseChips(pos_betArray[i]); //扣掉下注的籌碼
						System.out.println("Loss"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
					}
					if(host.getTotalValue()<allplayers[i].getTotalValue()) //玩家贏
					{
						allplayers[i].increaseChips(-pos_betArray[i]);
						System.out.println("Get"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
					}
					else if(host.getTotalValue()==allplayers[i].getTotalValue()) //平手
					{
						System.out.println("Chips have no change! The chips now is "+allplayers[i].getCurrentChips());
					}
				}
				else if(allplayers[i].getTotalValue()>21) //玩家爆掉
				{
					allplayers[i].increaseChips(pos_betArray[i]); 
					System.out.println("Loss"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
				}
			}
			else if(host.getTotalValue()>21) //莊家爆掉的情況下
			{
				if(allplayers[i].getTotalValue()<=21) //玩家沒有爆
				{
					allplayers[i].increaseChips(-pos_betArray[i]);
					System.out.println("Get"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
				}
				else if(allplayers[i].getTotalValue()>21) //玩家也爆掉
				{
					System.out.println("Chips have no change! The chips now is "+allplayers[i].getCurrentChips());
				}
			}
			
		}
		
	}
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}


}


