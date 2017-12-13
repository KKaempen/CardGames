import java.util.*;

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
		discardPile = new Card[0];
		winnings = 0;
	}
	
	public void deal() {
		yourHand = cardDeck.draw(2);
		dealerHand = cardDeck.draw(2);
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
		yourCardValue = 0;
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
		dealerCardValue = 0;
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
	
	public void playGame() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			while (true) {
				System.out.println("Place your bet, between $2 and $500.");
				bet = sc.nextInt();
				if (bet < 2) {
					System.out.println("Please bet something higher.");
				} else if (bet > 500) {
					System.out.println("Please bet something lower.");
				} else {
					break;
				}
			}
			this.deal();
			this.evaluateHands();
			System.out.println("Your hand:");
			for (int i = 0; i < yourHand.length; i++) {
				System.out.println(yourHand[i].getCard());
			}
			System.out.println("Dealer's card:");
			System.out.println(dealerHand[0].getCard());
			if (yourCardValue == 21) {
				if (dealerCardValue == 21) {
					System.out.println("You both have naturals. It's a standoff.");
					bet = 0;
					this.discard();
				} else {
					System.out.println("You have a natural. You win double your bet of $" + bet + "!");
					winnings += 2 * bet;
					bet = 0;
					this.discard();
				}
			} else if (dealerCardValue == 21) {
				System.out.println("The dealer has a natural. You lose your bet of $" + bet + ".");
				winnings -= bet;
				bet = 0;
				this.discard();
			} else {
				while (yourCardValue <= 21) {
					System.out.println("What would you like to do?");
					System.out.println("1. Hit");
					System.out.println("2. Stand");
					int choice = sc.nextInt();
					if (choice == 1) {
						this.hit(1);
						System.out.println("Your hand:");
						for (int i = 0; i < yourHand.length; i++) {
							System.out.println(yourHand[i].getCard());
						}
						this.evaluateHands();
					} else {
						break;
					}
				}
				if (yourCardValue > 21) {
					System.out.println("You have gone bust!");
				} else {
					while (dealerCardValue <= 16) {
						this.hit(0);
						System.out.println("The dealer hits and draws a " + dealerHand[dealerHand.length - 1].getCard() + ".");
						this.evaluateHands();
					}
					System.out.println("Dealer's hand:");
					for (int i = 0; i < dealerHand.length; i++) {
						System.out.println(dealerHand[i].getCard());
					}
				}
				int winner = this.getWinner();
				if (winner == -1) {
					System.out.println("It's a standoff!");
					bet = 0;
					this.discard();
				} else if (winner == 1) {
					System.out.println("You win the hand!");
					winnings += bet;
					bet = 0;
					this.discard();
				} else if (winner == 0) {
					System.out.println("The dealer wins the hand!");
					winnings -= bet;
					bet = 0;
					this.discard();
				}
			}
			System.out.println("Current winnings: $" + winnings);
			System.out.println("What would you like to do?");
			System.out.println("1. Play again");
			System.out.println("2. Cash out.");
			int choice = sc.nextInt();
			if (choice == 2) {
				break;
			}
		}
	}
} 
