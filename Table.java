import java.util.ArrayList;

public class Table 
{
	static final int MAXPLAYER = 4; // �̦h�u�৤�|�ӤH
	private Deck allcards;          //�Ҧ����P
	private Player[] allplayers;
	private ArrayList<Card> hostcard = new ArrayList<Card>(); //���a���쪺�P
	private ArrayList<ArrayList<Card>> pgcard = new ArrayList<ArrayList<Card>>(); //���a���쪺�P
	private Dealer host; // ���a
	private int[] pos_betArray = new int[MAXPLAYER]; // �C�Ӫ��a�b�@���U���`
	private int nDeck;

	public Table(int nDeck) 
	{
		this.nDeck = nDeck;
		this.allcards = new Deck(nDeck); //�����deck
		this.allplayers = new Player[MAXPLAYER]; //�����player
		pgcard.clear();
		for(int i=0;i<allplayers.length;i++)
		{
			pgcard.add(new ArrayList<Card>());
		}
	}

	public void set_player(int pos, Player p) // �N(��m,player)��Jallplayers array
	{
		allplayers[pos] = p;
	}

	public Player[] get_player() // �b��W��player
	{
		return allplayers;
	}
	
	public int[]get_players_bet() //���a���U�`
	{
		return pos_betArray;
	}
	
	public void set_dealer(Dealer d) // ����a���P��W
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
			pos_betArray[i] = allplayers[i].makeBet(); //��J�C�Ӫ��a�b�C�@���U����`
		}

	}

	private void distribute_cards_to_dealer_and_players() 
	{
		for (int i = 0; i < allplayers.length; i++) 
		{
			pgcard.get(i).add(allcards.getOneCard(true)); //�qallcards�����@�i�Ʃ��pgcard(�Ĥ@�i)
			pgcard.get(i).add(allcards.getOneCard(true)); //�qallcards�����@�i�Ʃ��pgcard(�ĤG�i)
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
		for (int i = 0; i < allplayers.length; i++) //�ݨC�@�Ӫ��a
		{
			if(allplayers[i].getTotalValue()<=21) //�p�G���a���P�S���z
			{
			boolean hit=false;
			System.out.println(allplayers[i].getName()+"'s Cards now:");
			for(Card c : pgcard.get(i)) 
			{
				c.printCard();
			}
			do
			{
				hit=allplayers[i].hit_me(this); //�n�P
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
				else //���n�P
				{
					System.out.println(" Pass hit!");
				}
			}
			while(hit);
			}
			System.out.println(allplayers[i].getName()+"'s hit is over!"); //���n�A�ݭn���n�P�F
		}
	}
	
	private void ask_dealer_about_hits()
	{
		boolean hit=host.hit_me(this);
		if(hit)
		{
			do
			{
				hit=host.hit_me(this); //�n�P
				if(hit)
				{
					hostcard.add(allcards.getOneCard(true));
					host.setOneRoundCard(hostcard);
				}
			}
			while(hit);
			System.out.println("Dealer's hit s over!");//���n�A�ݭn���n�P�F 
		}
		
    }
	
	private void calculate_chips()
	{
		System.out.print("Dealer's card value is " + host.getTotalValue() + ",Cards:" );
		host.printAllCard();
		for(int i=0;i<allplayers.length;i++)
		{
			System.out.println(allplayers[i].getName()+"card value is "+allplayers[i].getTotalValue());
			if(host.getTotalValue()<=21) //���a�S�z�����p�U
			{
				if(allplayers[i].getTotalValue()<=21) //���a�]�S���z
				{
					if(host.getTotalValue()>allplayers[i].getTotalValue()) //���aĹ
					{
						allplayers[i].increaseChips(pos_betArray[i]); //�����U�`���w�X
						System.out.println("Loss"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
					}
					if(host.getTotalValue()<allplayers[i].getTotalValue()) //���aĹ
					{
						allplayers[i].increaseChips(-pos_betArray[i]);
						System.out.println("Get"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
					}
					else if(host.getTotalValue()==allplayers[i].getTotalValue()) //����
					{
						System.out.println("Chips have no change! The chips now is "+allplayers[i].getCurrentChips());
					}
				}
				else if(allplayers[i].getTotalValue()>21) //���a�z��
				{
					allplayers[i].increaseChips(pos_betArray[i]); 
					System.out.println("Loss"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
				}
			}
			else if(host.getTotalValue()>21) //���a�z�������p�U
			{
				if(allplayers[i].getTotalValue()<=21) //���a�S���z
				{
					allplayers[i].increaseChips(-pos_betArray[i]);
					System.out.println("Get"+pos_betArray[i]+"Chips,the chips now is "+ allplayers[i].getCurrentChips());
				}
				else if(allplayers[i].getTotalValue()>21) //���a�]�z��
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


