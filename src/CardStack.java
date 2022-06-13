import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


abstract public class CardStack {
	
	private static ImageIcon blankCardIcon = new ImageIcon(Card.class.getResource("blankCard.png"));
	private ArrayList<Card> cardStack;
	private CardLabel blankCardLabel;
	private Point origin;
	private int x_offset;
	private int y_offset;
	private CardLabel blankCard;
	private JLayeredPane layeredPane;
	public enum Display {
		DISPLAY_TOP_CARD_ONLY,
		DISPLAY_ALL_CARDS
	}
	public enum CardDisplay {
		DISPLAY_BACK_ONLY,
		DISPLAY_FRONT_ONLY,
		DISPLAY_FRONT_AND_BACK
	}
	public enum RemovalOrder {
		LIFO,
		FIFO
	}
	public enum DragOption {
		ONE_CARD,
		MULTIPLE_CARDS,
		NONE
	}
	
	public DragOption getDragOption() {
		return dragOption;
	}


	private Display display;
	private CardDisplay cardDisplay;
	private RemovalOrder removalOrder;
	private DragOption dragOption;

	public CardStack(Point origin, int x_offset, int y_offset, Display display, CardDisplay cardDisplay, RemovalOrder removalOrder, 
			DragOption dragOption, JLayeredPane layeredPane) {
		
		this.origin = origin;
		this.x_offset = x_offset;
		this.y_offset = y_offset;
		this.display = display;
		this.cardDisplay = cardDisplay;
		this.removalOrder = removalOrder;
		this.dragOption = dragOption;
		this.layeredPane = layeredPane;
		cardStack = new ArrayList<Card>();
		blankCardLabel = new CardLabel(blankCardIcon, blankCardIcon, origin, false);
		layeredPane.add(blankCardLabel.getLabel(), new Integer(0));
		CardMap.put(blankCardLabel.getLabel(), this);
	}
	
	public static ImageIcon getBlankCard() { 
		return blankCardIcon;
	}

	public void addCard(Card card, boolean showFace) {
		
		cardStack.add(card);
		card.setParent(this);
		card.setParentLocation(size()-1);
		Point point = new Point(origin.x + (size()-1)*x_offset, origin.y + (size()-1)*y_offset);
		getCard().getCardLabel().setShowFace(showFace);
		JLabel label = getCard().getCardLabel().getLabel();
		label.setLocation(point.x, point.y);
		layeredPane.add(label, new Integer(size()));
	}
	
	public void addCard(Card card) {
		addCard(card, true);
	}

	public void dropCard(Card card) {
		cardStack.add(card);
		//	if (prevSize == 0 && cardDisplay.DISPLAY_BACK_ONLY && display == Display.DISPLAY_TOP_CARD_ONLY)
			card.setParent(this);
			card.setParentLocation(size()-1);
			Point point = new Point(origin.x + (size()-1)*x_offset, origin.y + (size()-1)*y_offset);
			JLabel label = getCard().getCardLabel().getLabel();
			label.setLocation(point.x, point.y);
			layeredPane.setLayer(label, size());
		
	}
	
	
	public void addCards(ArrayList<Card> cards){
		cardStack.addAll(cards);
	//	for (Card card: cards)
	//		addCard(card);
	}
	
	public Card getCard() {
		if (cardStack.size() == 0)
			return null;
		if (removalOrder == RemovalOrder.LIFO)
			return cardStack.get(size() - 1);
		else return cardStack.get(0);
	}
	
	public Card getCard(int i) {

		if (cardStack.size() > i)
			return cardStack.get(i);
		else return null;
	}
	
	public ArrayList<Card> getAllCards() {
		return cardStack;
	}
	
	public void removeCard() {
		if (removalOrder == RemovalOrder.LIFO)
			cardStack.remove(size() - 1);
		else  cardStack.remove(0);
	}
	
	public void removeCard(int i) {
		cardStack.remove(i);
	}
	
	public int size() {
		return cardStack.size();
	}
	
	public void clear() {
		JLabel label;
		for (Card card : getAllCards()) {
			label = card.getCardLabel().getLabel();
			layeredPane.remove(layeredPane.getIndexOf(label));
		}
		layeredPane.revalidate();
		layeredPane.repaint();
		cardStack.clear();
	}

	public Point calulateOffset(Point origin, int index) {
		return new Point(origin.x + (index)*x_offset, origin.y + (index)*y_offset);
	}
	
	public boolean validateDrag(DragItems dragItems) {
		return true;
	}
	
	 public boolean validateDrop(DragItems dragItems) {
	 	return true;
	 }
	 
	 public CardLabel getBlankCardLabel() {
		 return blankCardLabel;
	 }
	 
	 public abstract void mouseClick();
	 
}