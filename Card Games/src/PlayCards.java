import java.util.*;

public class PlayCards {
	
	public static boolean playWar() {
		Scanner sc = new Scanner(System.in);
		WarGame game = new WarGame();
		game.beginGame();
		System.out.println("Game over. Would you like to play again?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int choice = sc.nextInt();
		if (choice == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean playSlapjack() {
		Scanner sc = new Scanner(System.in);
		SlapJack game = new SlapJack();
		game.initializeGame();
		game.playGame();
		System.out.println("Game over. Would you like to play again?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int choice = sc.nextInt();
		if (choice == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean playBlackjack() {
		Scanner sc = new Scanner(System.in);
		Blackjack game = new Blackjack();
		game.playGame();
		System.out.println("Game over. Would you like to play again?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int choice = sc.nextInt();
		if (choice == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		while (true) {
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
				boolean again = playWar();
				if (!again) {
					break;
				}
			} else if (game == 2) {
				boolean again = playSlapjack();
				if (!again) {
					break;
				}
			} else if (game == 3) {
				boolean again = playBlackjack();
				if (!again) {
					break;
				}
			}
		}
		System.out.println("Thank you for playing!");
	}
}
