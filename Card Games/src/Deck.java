public class Deck {
	
	public static String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
	
	public static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
	public Card[] cardDeck = {};
	
	public Deck() {
		this.cardDeck = new Card[suits.length * ranks.length];
		int count = 0;
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				this.cardDeck[count++] = new Card(suits[i], ranks[j]);
			}
		}
	}
	
	public void shuffle() {
		for (int i = 0; i < this.cardDeck.length - 1; i++) {
			int val = (int) Math.floor(Math.random() * (cardDeck.length - i - 1));
			int swapInd = i + val + 1;
			Card tempCard = this.cardDeck[i];
			this.cardDeck[i] = this.cardDeck[swapInd];
			this.cardDeck[swapInd] = tempCard;
		}
	}
	
	public Card remove(final Card card) {
		int removeInd = -1;
		Card tempCard = null;
		for (int i = 0; i < this.cardDeck.length; i++) {
			if (this.cardDeck[i].equals(card)) {
				tempCard = cardDeck[i];
				removeInd = i;
			}
		}
		if (removeInd == -1) {
			return tempCard;
		}
		Card[] newDeck = new Card[this.cardDeck.length - 1];
		int count = 0; 
		for (int i = 0; i < this.cardDeck.length; i++) {
			if (i != removeInd) {
				newDeck[count++] = this.cardDeck[i];
			}
		}
		this.cardDeck = newDeck;
		return tempCard;
	}
	
	public void add(final Card card) {
		if (card != null) {
			Card[] newDeck = new Card[this.cardDeck.length + 1];
			for (int i = 0; i < this.cardDeck.length; i++) {
				newDeck[i] = this.cardDeck[i];
			}
			newDeck[newDeck.length - 1] = card;
			this.cardDeck = newDeck;
		}
	}
	
	public String printCards() {
		String returnString = "";
		for (int i = 0; i < this.cardDeck.length; i++) {
			returnString += this.cardDeck[i].getCard() + "\n";
		}
		return returnString;
	}

	public static void main(String[] args) {
		Deck newDeck = new Deck();
		System.out.println(newDeck.printCards());
		Card card1 = newDeck.cardDeck[35];
		newDeck.remove(card1);
		System.out.println(newDeck.printCards());
		newDeck.shuffle();
		System.out.println("SHUFFLED:");
		System.out.println(newDeck.printCards());
		newDeck.add(card1);
		System.out.println(newDeck.printCards());
	}
	
}
