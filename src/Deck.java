import java.net.URL;
import java.util.*;

import javax.swing.ImageIcon;

public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();	
	
	public Deck() {
	
		String [] numbers = Card.getValues();
		int imageNumber = 1;
		for (Card.Suits suit : Card.Suits.values()) {
			for (int i=0; i<numbers.length; i++) {
				deck.add(new Card(suit, i, imageNumber++));		
			}
		}
	}

	public void shuffle() {
			Collections.shuffle(deck);
	}
	
	public String toString() {
		
		String cards = "";
		for (Card card : deck)
			cards += card.toString() + "\n";
		return(cards);
	}
	
	public Card getCard() {
		return deck.get(0);
	}
	
	public Card removeCard() {
		return deck.remove(0);
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}
}

