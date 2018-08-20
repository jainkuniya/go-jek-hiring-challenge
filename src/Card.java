
public class Card {
	private String face;
	private String suit;
	private static final int[] aceScores = {1, 11};
	
	public Card(String suit,String face){
		this.suit = suit;
		this.face = face;
	}
	
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	
	public String getInReadableFormat(){
		return suit + face;
	}

	public int getNonAceScore() {
		return face== "J" || face == "Q" || face == "K" ? 10 : Integer.parseInt(face);
	}
	
	public int[] getAceScore(){
		return aceScores;
	}
}
