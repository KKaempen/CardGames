import java.util.*;

public class PlayCards {
	
	public static void playWar() {
		
	}
	
	public static void playSlapjack() {
		
	}
	
	public static void playBlackjack() {
		Scanner sc = new Scanner(System.in);
		Blackjack game = new Blackjack();
		while (true) {
			System.out.println("Place your bet, between $2 and $500.");
			game.bet = sc.nextInt();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to cards! Which game would you like to play? Type the integer next to your desired game.");
		System.out.println("1. War");
		System.out.println("2. Slapjack");
		System.out.println("3. Blackjack");
		
		int game;
		
		while (true) {
			game = sc.nextInt();
			if (game < 1 || game > 3) {
				System.out.println("Please choose again.");
				System.out.println("1. War");
				System.out.println("2. Slapjack");
				System.out.println("3. Blackjack");
			} else {
				break;
			}
		}
		
		if (game == 1) {
			playWar();
		} else if (game == 2) {
			playSlapjack();
		} else if (game == 3) {
			playBlackjack();
		}
		
	}
}
