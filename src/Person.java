import java.util.ArrayList;

public class Person {
	// public abstract void turn();
	private String name;
	private ArrayList<Card> cards;
	private int score = 0;
	
	public Person(String name){
		this.name = name;
		cards = new ArrayList<Card>();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public String getName() {
		return name;
	}

	public int calculateScore() {
		int score = 0;
		// int aceCount = 0;
		for(int i=0; i< cards.size(); i++) {
			Card card  =   cards.get(i);
			// if it is ace choose preferably
			if (card.getFace() == "A") {
				if (score + 11 == 21) {
					score += 1;
				}else {
					score += 11;
				}
				// aceCount++;
			}else {
				score += card.getNonAceScore();
			}
		}

		
		return score;
	}

	public void setScore() {
		this.score = calculateScore();
	}
	
	public void printCard() {
		System.out.print(getName() + "'s cards:");
		for(int i=0; i<getCards().size(); i++){
			if (i== 0  && getName() == "dealer") {
				System.out.print("...");
	
			}else 
			{
				System.out.print(getCards().get(i).getInReadableFormat() + " ");
			}
		}
		System.out.println("");
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
