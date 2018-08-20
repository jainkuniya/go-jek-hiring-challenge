import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack {
	
	ArrayList<Card> cards;
	private Scanner in;
	private static final int BLACK_JACK = 21;
	
	public static void main(String[] params) {
		BlackJack blackjack = new BlackJack();
		blackjack.play();
		
	}
	
	public void play(){
		in = new Scanner(System.in);
		
		int numOfDecks = 0;
		// Enter number of decks
		while(numOfDecks<4) {
			System.out.println("Enter number of decks (4 or more)");
			numOfDecks = in.nextInt();
		}
		
		// create decks 
		createAllCards(numOfDecks);
		
		Person player = new Person("player");
		Person dealer = new Person("dealer");
		
		allocateCard(player, 2);
		allocateCard(dealer, 2);
		
		// printPersonCard(player);
		// printPersonCard(dealer);
		
		playersTurn(player);
		
		dealerTurn(dealer);
		
		// evaluate result
		evalauteResult(player, dealer);
		
	}

	private void evalauteResult(Person player, Person dealer) {
		int playerScore = player.getScore();
		int dealerScore = dealer.getScore();
		System.out.println("\n\nPlayer's score: " + player.getScore() + "\n" + "Dealer score: " + dealer.getScore());
		if(playerScore > 21) {
			System.out.println("Player is bursted so looses, dealer wins");
		}else if (dealerScore > 21) {
			System.out.println("Dealer is bursted so looses, player wins");
		}else if(playerScore == 21 && dealerScore == 21) {
			System.out.println("Both BLACK_JACK: draw");
		}else if(playerScore == 21) {
			System.out.println("Player wins as score is BLACK_JACK");
		}else if (dealerScore == 21) {
			System.out.println("Dealer wins as score is BLACK_JACK");
		}else {
			int dealerDiff = Math.abs(dealerScore - 21);
			int playerDiff = Math.abs(playerScore -21);
			
			if (dealerDiff < playerDiff) {
				System.out.println("Dealer wins as score is closer to 21");
			}else if (dealerDiff > playerDiff) {
				System.out.println("Player wins as score is closer to 21");
			}else {
				System.out.println("Draw as diff is same.");
			}
		}
		
	}

	private void dealerTurn(Person dealer) {
		System.out.println("\nDealer's turn:");
		int score = dealer.getScore();
		System.out.println("Dealer's score: " + score);
		if(score<= 17) {
			System.out.println("Hitting as score is <= 17");
			allocateCard(dealer, 1);
			dealerTurn(dealer);
		}else {
			return;
		}
	}

	private void playersTurn(Person player) {
		System.out.println("\nPlayer's turn: Enter 0 to HIT, 1 to STAND");
		int input = in.nextInt();
		if(input == 0) {
			allocateCard(player, 1);
			// check for burst
			boolean[] bursted = checkForBurstAndBlackJackScore(player);
			if (bursted[0]) {
				System.out.println("Player looses, dealer wins");
				System.exit(0);
				return;
			}
			if (bursted[1]) {
				System.out.println("As score is 21, i.e BLACK_JACK, player's win so now STAND");
				System.exit(0);
				return;
			}
			playersTurn(player);
		}else if(input == 1) {
			return;
		}else {
			System.out.println("PLease enter valid command");
			playersTurn(player);
		}	
	}

	private boolean[] checkForBurstAndBlackJackScore(Person person) {
		int score = person.getScore();
		
		System.out.println(person.getName() + "'s score " + score);
		if(score > BLACK_JACK) {
			System.out.println(person.getName() + " Busted");
			boolean[] arr = {true, false};
			return arr;
		}else {
			boolean[] arr = {false, score == BLACK_JACK};
			return arr;
		}
	}

	private void allocateCard(Person person, int num) {
		for(int i=0; i<num; i++){
			person.getCards().add(cards.get(0));
			cards.remove(0);
		}
		person.setScore();
		person.printCard();
	}

	private void createAllCards(int numOfDecks) {
		cards = new ArrayList<Card>();
		for(int i=0; i< numOfDecks; i++){
			Deck d = new Deck();
			cards.addAll(d.getCards());
		}
		shuffAllCards();
	}

	private void shuffAllCards() {
		Collections.shuffle(cards);
		System.out.println("Successfully shuffled all cards\n");
	}
}
