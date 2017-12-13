import java.util.*;

public class WarGame {
	
	Player p1, p2;
	Card p1Card, p2Card;
	Deck theDeck;
	ArrayList <Card> p1Pile, p2Pile;
	boolean startAWar;
	
	public WarGame() {
		startAWar = false;
		p1Pile = new ArrayList<Card>();
		p2Pile = new ArrayList<Card>();
		theDeck = new Deck();
		p1 = new Player();
		p2 = new Player();
		theDeck.shuffle();
		Card[] cards1 = theDeck.draw(26);
		Card[] cards2 = theDeck.draw(26);
		for (int i = 0; i < 26; i++)
		{
			p1.getCard(cards1[i]);
			p2.getCard(cards2[i]);
		}
	}
	
	public static int compareTo(final Card card1, final Card card2) {
		int card1Val;
		int card2Val;
		String rank1 = card1.getRank();
		String rank2 = card2.getRank();
		if (rank1.equals("Ace")) {
			card1Val = 14;
		} else if (rank1.equals("King")) {
			card1Val = 13;
		} else if (rank1.equals("Queen")) {
			card1Val = 12;
		} else if (rank1.equals("Jack")) {
			card1Val = 11;
		} else {
			card1Val = Integer.parseInt(rank1);
		}
		if (rank2.equals("Ace")) {
			card2Val = 14;
		} else if (rank2.equals("King")) {
			card2Val = 13;
		} else if (rank2.equals("Queen")) {
			card2Val = 12;
		} else if (card2.getRank().equals("Jack")) {
			card2Val = 11;
		} else {
			card2Val = Integer.parseInt(card2.getRank());
		}
		return card1Val - card2Val;
	}

	public void beginGame() {
		int rounds = 0; 
		Scanner sc = new Scanner(System.in);
		String winner = "";
		while (true) {
			if (rounds > 100) {
				if (p1.cardCount() > p2.cardCount()) {
					winner = "You";
					break;
				} else if (p1.cardCount() < p2.cardCount()) {
					winner = "Computer";
					break;
				}
			}
			if (p1.cardCount() < 1) {
				winner = "Computer";
				break;
			} else if (p2.cardCount() < 1) {
				winner = "You";
				break;
			}
			System.out.println("You have " + p1.cardCount() + " cards.");
			System.out.println("Computer has " + p2.cardCount() + " cards.");
			System.out.println("Press enter to flip your top card.");
			String none = sc.nextLine();
			rounds += 1;
			p1Card = p1.playCard();
			p2Card = p2.playCard();
			p1Pile.add(p1Card);
			p2Pile.add(p2Card);
			System.out.println(p1Card.getCard() + " vs. " + p2Card.getCard());
			if (compareTo(p1Card, p2Card) > 0) {
				System.out.println("You win the matchup.");
				p1.getCard(p1Card);
				p1.getCard(p2Card);
				p1Pile.clear();
				p2Pile.clear();
			} else if (compareTo(p1Card, p2Card) < 0) {
				System.out.println("Computer wins the matchup.");
				p2.getCard(p1Card);
				p2.getCard(p2Card);
				p1Pile.clear();
				p2Pile.clear();
			} else {
				startAWar = true;
				rounds += 1;
			}
			while (startAWar) {
				System.out.println("War! Press enter to flip two cards from the top of your pile.");
				none = sc.nextLine();
				p1Pile.add(p1.playCard());
				p2Pile.add(p2.playCard());
				p1Card = p1.playCard();
				p2Card = p2.playCard();
				p1Pile.add(p1Card);
				p2Pile.add(p2Card);
				System.out.println(p1Card.getCard() + " vs. " + p2Card.getCard());
				if (compareTo(p1Card, p2Card) > 0) {
					System.out.println("You win the war!");
					for (int i = 0; i < p1Pile.size(); i++) {
						p1.getCard(p1Pile.get(i));
					}
					for (int i = 0; i < p2Pile.size(); i++) {
						p1.getCard(p2Pile.get(i));
					}
					p1Pile.clear();
					p2Pile.clear();
					startAWar = false;
				} else if (compareTo(p1Card, p2Card) < 0) {
					System.out.println("Computer wins the war!");
					for (int i = 0; i < p1Pile.size(); i++) {
						p2.getCard(p1Pile.get(i));
					}
					for (int i = 0; i < p2Pile.size(); i++) {
						p2.getCard(p2Pile.get(i));
					}
					p1Pile.clear();
					p2Pile.clear();
					startAWar = false;
				}
			}
		}
		if (winner.equals("You")) {
			System.out.println("You've won the game!");
		} else if (winner.equals("Computer")) {
			System.out.println("The computer has won the game!");
		}
	}
}