
public class Blackjack {
	
	public Card[] yourHand;
	
	public Card[] dealerHand;
	
	public Card[] discardPile;
	
	public Deck cardDeck;
	
	public int yourHandSoft;
	
	public int dealerHandSoft;
	
	public int yourHandHard;
	
	public int dealerHandHard;
	
	public int yourCardValue;
	
	public int dealerCardValue;
	
	public int bet;
	
	public int winnings;
	
	public static final int MAX_BET = 500;
	
	public static final int MIN_BET = 2;
	
	public Blackjack() {
		cardDeck = new Deck();
		cardDeck.shuffle();
		yourHand = cardDeck.draw(2);
		dealerHand = cardDeck.draw(2);
		discardPile = new Card[0];
		for (int i = 0; i < yourHand.length; i++) {
			if (yourHand[i].getRank().equals("Ace")) {
				yourHandSoft += 1;
			}
		}
		for (int i = 0; i < dealerHand.length; i++) {
			if (dealerHand[i].getRank().equals("Ace")) {
				dealerHandSoft += 1;
			}
		}
	}
	
	public void evaluateHands() {
		for (int i = 0; i < yourHand.length; i++) {
			String rank = yourHand[i].getRank();
			if (rank.equals("Queen") || rank.equals("King") || rank.equals("Jack")) {
				yourCardValue += 10;
			}
			else if (!rank.equals("Ace")) {
				yourCardValue += Integer.parseInt(rank);
			}
		}
		yourCardValue += 11 * yourHandSoft;
		yourCardValue += 1 * yourHandHard;
		while (yourCardValue > 21) {
			if (yourHandSoft > 0) {
				yourHandSoft -= 1;
				yourHandHard += 1;
				yourCardValue -= 10;
			} else {
				break;
			}
		}
		for (int i = 0; i < dealerHand.length; i++) {
			String rank = dealerHand[i].getRank();
			if (rank.equals("Queen") || rank.equals("King") || rank.equals("Jack")) {
				dealerCardValue += 10;
			}
			else if (!rank.equals("Ace")) {
				dealerCardValue += Integer.parseInt(rank);
			}
		}
		dealerCardValue += 11 * dealerHandSoft;
		dealerCardValue += 1 * dealerHandHard;
		while (dealerCardValue > 21) {
			if (dealerHandSoft > 0) {
				dealerHandSoft -= 1;
				dealerHandHard += 1;
				dealerCardValue -= 10;
			} else {
				break;
			}
		}
	}
	
	public void hit(final int player) {
		if (player == 1) {
			Card[] tempArray = new Card[yourHand.length + 1];
			for (int i = 0; i < yourHand.length; i++) {
				tempArray[i] = yourHand[i];
			}
			if (cardDeck.cardDeck.length == 0) {
				cardDeck.cardDeck = discardPile;
				cardDeck.shuffle();
				discardPile = new Card[0];
			}
			tempArray[tempArray.length - 1] = cardDeck.draw(1)[0];
			yourHand = tempArray;
		} else if (player == 0) {
			Card[] tempArray = new Card[dealerHand.length + 1];
			for (int i = 0; i < dealerHand.length; i++) {
				tempArray[i] = dealerHand[i];
			}
			if (cardDeck.cardDeck.length == 0) {
				cardDeck.cardDeck = discardPile;
				cardDeck.shuffle();
				discardPile = new Card[0];
			}
			tempArray[tempArray.length - 1] = cardDeck.draw(1)[0];
			dealerHand = tempArray;
		}
	}
	
	public int getWinner() {
		if (dealerCardValue == 21) {
			return 0;
		}
		if (yourCardValue == 21) {
			return 1;
		}
		if (yourCardValue > 21) {
			return 0;
		}
		if (dealerCardValue > 21) {
			return 1;
		}
		if (yourCardValue > dealerCardValue) {
			return 1;
		}
		if (yourCardValue < dealerCardValue) {
			return 0;
		}
		return -1;
	}

	public void discard() {
		Card[] tempDiscardPile = new Card[discardPile.length + dealerHand.length + yourHand.length];
		int count = 0; 
		for (int i = 0; i < discardPile.length; i++) {
			tempDiscardPile[count++] = discardPile[i];
		}
		for (int i = 0; i < dealerHand.length; i++) {
			tempDiscardPile[count++] = dealerHand[i];
		}
		for (int i = 0; i < yourHand.length; i++) {
			tempDiscardPile[count++] = yourHand[i];
		}
		discardPile = tempDiscardPile;
	}
	
} 
