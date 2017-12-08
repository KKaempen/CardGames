public class Card {
	
	private String rank;
	
	private String suit;
	
	public Card() {
		this.rank = "";
		this.suit = "";
	}
	
	public Card(final String newSuit, final String newRank) {
		this.suit = newSuit;
		this.rank = newRank;
	}
	
	public String getCard() {
		if (this == null || this.suit == "" || this.rank == "") {
			return "Not a valid card.";
		} else {
			return this.rank + " of " + this.suit;
		}
	}
	
	public static void main(String[] args) {
		Card card1 = new Card("Hearts", "Queen");
		Card card2 = new Card("Spades", "Ace");
		Card card3 = new Card();
		
		System.out.println(card1.getCard());
		System.out.println(card2.getCard());
		System.out.println(card3.getCard());
	}
}
