import java.awt.Point;
import java.util.ArrayList;

public class DragItems {

	public enum DragLevel {
		ENTIRE_STACK, BOTTOM_CARD_ONLY;
	}
	
	
	private ArrayList<Card> dragCards;
	private Point originalLocation;
	private boolean OKToDrag;
	private CardStack dragParent;
	private DragLevel dragLevel;
	
	
	public DragItems() {
		dragCards = new ArrayList<Card>();
	}
	
	public DragLevel getDragLevel() {
		return dragLevel;
	}
	
	public void setDragLevel(DragLevel dragLevel) {
		this.dragLevel = dragLevel;
	}
	
	public CardStack getDragParent() {
		return dragParent;
	}
	
	public void setDragParent(CardStack parent) {
		dragParent = parent;
	}
	
	public ArrayList<Card> getCards() {
		return dragCards;
	}

	public Point getOriginalLocation() {
		return originalLocation;
	}

	public void setOriginalLocation(Point originalLocation) {
		this.originalLocation = originalLocation;
	}
	
	public void add(Card card) {
		dragCards.add(card);
	}
	
	public Card getCard(int index) { 
		return dragCards.get(index);
	}
	
	public Card getCard() {
		return dragCards.get(0);
	}
	
	public void clear() {
		dragCards.clear();
		originalLocation = null;
	}
	
	public int getSize() {
		return dragCards.size();
	}
	
	public void setOKToDrag(boolean OKToDrag) {
		this.OKToDrag = OKToDrag;
	}
	
	public boolean getOKToDrag() {
		return OKToDrag;
	}
}
