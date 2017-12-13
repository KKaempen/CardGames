import java.util.ArrayList;

public class Player {
	ArrayList<Card> myCards;
	
	public Player() {
		myCards = new ArrayList<Card>(26);
	}
	
	public void getCard(Card aCard) {
		myCards.add(aCard);
	}
	
	public Card playCard() {
		return myCards.remove(0);
	}
	
	public Card seeCard(int position) {
		return myCards.get(position);
	}
	
	public int cardCount() {
		return myCards.size();
	}
}
