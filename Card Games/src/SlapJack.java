
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SlapJack {
	public Deck deck = new Deck();
	public ArrayList<Card> userCards = new ArrayList<>();
	public ArrayList<Card> compCards = new ArrayList<>();
	
	private ArrayList<Card> centerPile = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();
	
	public SlapJack() {
		
	}
	
	public void initializeGame() {
		deck.shuffle();
		for (int i = 0; i < deck.cardDeck.length; i++) {
			if (i < deck.cardDeck.length / 2) {
				userCards.add(deck.cardDeck[i]);
			} else {
				compCards.add(deck.cardDeck[i]);
			}
		}
	}
	
	public void playCard(ArrayList<Card> cards, boolean userTurn) {
		Card topCard = cards.remove(0);
		if (userTurn) {
			System.out.println("You put down " + topCard.getRank());
		} else {
			System.out.println("Computer put down " + topCard.getRank());
		}
		if (topCard.getRank() == "Jack") {
			long currentTime = System.currentTimeMillis();
			String newInput = scanner.nextLine();
			if (System.currentTimeMillis() - currentTime < random.nextDouble() * 500 + 250) {
				System.out.println("You won that slap!");
				
				// Adds cards in center to user's hand
				userCards.addAll(centerPile);
			} else {
				System.out.println("Computer won that slap!");
				
				// Adds cards in center to computer's hand
				compCards.addAll(centerPile);
			}
			centerPile.clear(); 
		} else {
			centerPile.add(topCard);
		}
	}
	
	public void playGame() {
		boolean isUserTurn = true;
		
		while (userCards.size() > 0 && compCards.size() > 0) {
			System.out.println("Computer has " + compCards.size() + " cards");
			System.out.println("You have " + userCards.size() + " cards");

			if (isUserTurn) {
				System.out.println("Press enter to deal a card, and if it's a jack press enter again to slap it");
				String userInput = scanner.nextLine();
				playCard(userCards, isUserTurn);
			} else {
				playCard(compCards, isUserTurn);
			}	

			isUserTurn = !isUserTurn;
		}
		if (userCards.size() == 0) {
			System.out.println("You lost!");
		} else if (compCards.size() == 0) {
			System.out.println("You won!");
		}
	}
	
}
