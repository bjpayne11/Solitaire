import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Solitaire {

	private Deck deck;
	private Foundation foundations[];
	private Tableau tableau[];
	private Stock stock;
	private Talon talon;
	private JLayeredPane layeredPane;
	private JPanel panel; 
	private LayeredPaneListener layeredPaneListener;
	private DragItems dragItems = new DragItems();

	public Solitaire(JPanel panel) {

		deck = new Deck();
		this.panel = panel;
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(1000, 600));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Solitaire"));
		//		stock = new Stock(new Point(10, 30), layeredPane, new StockListener());
		talon = new Talon(new Point(160, 30), layeredPane);
		stock = new Stock(new Point(10, 30), layeredPane, talon);
		foundations = new Foundation[4];
		for (int i=0; i<4; i++)
			foundations[i] = new Foundation(new Point(i*150+450, 30), layeredPane);
		tableau = new Tableau[7];
		for (int i=0; i<7; i++)
			tableau[i] = new Tableau(new Point (i*150, 200), layeredPane);
		layeredPaneListener = new LayeredPaneListener();
		layeredPane.addMouseListener(layeredPaneListener);
		layeredPane.addMouseMotionListener(layeredPaneListener);
	}

	private void deal() {

		deck.shuffle();
		Card card;
		for (int i=0; i<7; i++) {
			card = deck.getCard();
			tableau[i].addCard(card, true);
			deck.removeCard();
			for (int j=i+1; j<7; j++) {
				card = deck.getCard();
				tableau[j].addCard(card, false);
				deck.removeCard();		
			}			
		}
		stock.addCards(deck.getDeck());
		stock.getBlankCardLabel().getLabel().setEnabled(true);
		stock.getBlankCardLabel().getLabel().setIcon(Card.getCardBack());
		panel.add(layeredPane);	
	}

	class LayeredPaneListener extends MouseInputAdapter {

		public void mousePressed(MouseEvent e) {

			dragItems = null;
			Component component = layeredPane.getComponentAt(e.getX(), e.getY());

			if (component instanceof JLabel) {
				Object item = CardMap.get((JLabel) component);
				if (item != null && item instanceof Card) {
					// are too many being dragged?		
					dragItems = new DragItems();
					dragItems.setOriginalLocation(component.getLocation());
					CardStack parent = ((Card) item).getParent();
					dragItems.setDragParent(parent);
					if (parent.getDragOption() == CardStack.DragOption.ONE_CARD  && (parent.size() - ((Card)item).getParentLocation() > 1)) 
						dragItems.setOKToDrag(false);
					// check if dragging the back of a card
					else if (!((Card)item).getCardLabel().getShowFace())
						dragItems.setOKToDrag(false);
					else dragItems.setOKToDrag(true);
					if (dragItems.getOKToDrag()) {
						for (int i = ((Card)item).getParentLocation(); i<parent.size(); i++) {
							Card card = parent.getCard(i);
							dragItems.add(card);
							JLabel label = card.getCardLabel().getLabel();
							layeredPane.setLayer(label, JLayeredPane.DRAG_LAYER + JLayeredPane.getLayer(label));
						}
						parent.validateDrag(dragItems);
					}
					else dragItems = null;
				}
			}
		}

		public void mouseReleased(MouseEvent e) {

			boolean okToMove = false;
			if (dragItems != null) {
				Component dropComponent = layeredPane.getComponentAt(e.getX(), e.getY());
				if (dropComponent instanceof JLabel) {
					Object dropItem = CardMap.get((JLabel) dropComponent);
					if (dropItem != null) { 
						CardStack parent;
						if (dropItem instanceof Card)
							parent = ((Card)dropItem).getParent();
						else parent = ((CardStack) dropItem);
						if (parent.validateDrop(dragItems)) {
							// where it's being dropped - check if drop allowed and allows # of dragged items
							for (int i=0; i<dragItems.getCards().size(); i++) {
								Card card = dragItems.getCard(i);
								card.getParent().removeCard();
								if (dropItem instanceof Card)
									((Card)dropItem).getParent().dropCard(card);  // move to drop stack
								else ((CardStack) dropItem).dropCard(card);
								okToMove = true;
							}
							// see if need to flip top card
							Card topCard = dragItems.getDragParent().getCard();
							if (topCard != null && topCard.getCardLabel().getShowFace() == false) {
								topCard.getCardLabel().flipCard();
							}
						}
					}
				}
				if (!okToMove && dragItems != null) {
					Point origin = new Point(dragItems.getOriginalLocation());
					for (int i=0; i<dragItems.getCards().size(); i++) {
						Card card = dragItems.getCard(i);
						// this should use setPoint in CardLabel
						JLabel label = card.getCardLabel().getLabel();
						label.setLocation(card.getParent().calulateOffset(origin, i));
						layeredPane.setLayer(label, JLayeredPane.getLayer(label) - JLayeredPane.DRAG_LAYER);
					}
				}
				dragItems = null;
			}
		}

		public void mouseDragged(MouseEvent e) {

			if (dragItems != null) {
				Point origin = new Point(e.getX() + 5, e.getY() + 5);
				for (int i=0; i<dragItems.getCards().size(); i++) {
					Card card = dragItems.getCard(i);
					// this should use setPoint in CardLabel
					JLabel label = card.getCardLabel().getLabel();
					label.setLocation(card.getParent().calulateOffset(origin, i));
				}
			}
		}


		public void mouseClicked(MouseEvent e) {

			Component clickComponent = layeredPane.getComponentAt(e.getX(), e.getY());
//			System.out.println(clickComponent);
			if (clickComponent instanceof JLabel) {
				Object clickItem = CardMap.get((JLabel) clickComponent);
				if (clickItem != null) { 
					CardStack parent;
					if (clickItem instanceof Card)
						parent = ((Card)clickItem).getParent();
					else {
						parent = ((CardStack) clickItem);
						parent.mouseClick();
					}
				}

			}
		}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();  // Stock, Talon, Foundations
		Solitaire solitaire = new Solitaire(panel);
		panel.setBackground(Color.green);
		solitaire.deal();
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
}