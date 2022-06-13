import java.awt.Point;
import java.net.URL;
import javax.swing.ImageIcon;

public class Card {
	
	public enum Suits {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}
	
	public enum Color {
		RED, BLACK;
	}
	
	private CardLabel cardLabel;
	
	private static String values[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private int number;
	private Suits suit;
	private Color color;
	private ImageIcon cardFace;
	private CardStack parent;
	private int parentLocation;
	
	static private ImageIcon cardBack = new ImageIcon(Card.class.getResource("b2fv.png"));

	
	public static ImageIcon getCardBack() { 
		return cardBack;
	}
	
	public CardStack getParent() {
		return parent;
	}


	public void setParent(CardStack parent) {
		this.parent = parent;
	}

	public int getParentLocation() {
		return parentLocation;
	}
	
	public void setParentLocation(int index) {
		parentLocation = index;
	}

	public Card(Suits suit, int number, int imageNumber) {
	
		if (suit == Suits.SPADES || suit == Suits.CLUBS)
			color = Color.BLACK;
		else color = Color.RED;
		this.number = number;
		this.suit = suit;
		String imageFile = imageNumber + ".png";

		URL imageURL = getClass().getResource(imageFile);
		this.cardFace = new ImageIcon(imageURL);
		cardLabel = new CardLabel(cardFace, cardBack);
		CardMap.put(cardLabel.getLabel(), this);
	}
	
	public CardLabel getCardLabel() { 
		return cardLabel;
	}
	
	public ImageIcon getCardFace() {
		return cardFace;
	}

	public int getNumber() {
		return number;
	}
	
	public Suits getSuit() {
		return suit;
	}
	
	public Color getColor() {
		return color;
	}
	
	static public String[] getValues() {
		return values;
	
	}
	
	public String toString() {
		return ("");
		//return(number + " " + suit + " " + showFace);
	}
	
}

