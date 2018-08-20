import java.util.ArrayList;

public class Deck {
	public static final int NUMBER_OF_CARDS = 52;
	public static final String[] suits = {"D", "C", "S", "H"};
	public static final String[] faces = {
			"A",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9",
			"10",
			"J",
			"Q",
			"K"
	};
	
	private ArrayList<Card> cards;
	
	public Deck(){
		cards = new ArrayList<Card>();
		for (String suit : suits) { 
			for (String face : faces) { 
				cards.add(new Card(suit, face));
			}
		}
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
}
