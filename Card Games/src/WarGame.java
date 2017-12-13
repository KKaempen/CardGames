import java.util.ArrayList;

public class WarGame {
	
	Player p1, p2;
	Card p1Card, p2Card;
	Deck theDeck;
	ArrayList <Card> p1Pile, p2Pile;
	boolean startAWar;
	
	public WarGame()
	{
		startAWar = false;
		p1Pile = new ArrayList<Card>();
		p2Pile = new ArrayList<Card>();
		p1 = new Player();
		p2 = new Player();
		theDeck.shuffle();
		for (int i = 0; i < 26; i++)
		{
			p1.getCard(theDeck.deal());
			p2.getCard(theDeck.deal());
		}
		beginGame();
	}

private void beginGame()
{
	String winner = "";
	while (true)
	{
		if (!startAWar)
		{
			if (p1.cardCount() > 0)
				p1Pile.add(p1.playCard());
			else
			{
				winner = "p2";
				break;
			}
			if (p2.cardCount() > 0)
				p2Pile.add(p2.playCard());
			else
			{
				winner = "p1";
				break;
			}
		}
		startAWar = false;
		p1Card = p1Pile.get(p1Pile.size() - 1);
		p2Card = p2Pile.get(p2Pile.size() - 1);
		
		if (p1Card.compareTo(p2Card) > 0)
			winner = "p1";
		else if (p1Card.compareTo(p2Card) < 0)
			winner = "p2";
		else
			winner = "none";
		
		if (winner.equals("p1"))
		{
			while (p1Pile.size() > 0)
				p1.getCard(p1Pile.remove(0));
			while (p2Pile.size() > 0)
				p1.getCard(p2Pile.remove(0));
		}
		
		if (winner.equals("p2"))
		{
			while (p1Pile.size() > 0)
				p2.getCard(p1Pile.remove(0));
			while (p2Pile.size() > 0)
				p2.getCard(p2Pile.remove(0));
		}
		
		if (winner.equals("none"))
		{
			justWarred = true;
			int count = 0;
			
			while (p1.cardCount() > 0 && count < 4)
			{
				p1Pile.add(p1.playCard());
				count++;
			}
			count = 0;
			while (p2.cardCount() > 0 && count < 4)
			{
				p2Pile.add(p2.playCard());
				count++;
			}
		}
	}
	System.out.println("The winner is "+winner+".");
	System.out.println("P1 has "+p1.cardCount()+ "cards. P2 has "+p2.cardCount()+" cards.");
}
}