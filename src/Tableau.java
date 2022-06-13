import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class Tableau extends CardStack {

	public Tableau(Point origin, JLayeredPane layeredPane) {
		super(origin, 0, 20, Display.DISPLAY_ALL_CARDS, CardDisplay.DISPLAY_FRONT_AND_BACK, RemovalOrder.LIFO, 
				DragOption.MULTIPLE_CARDS, layeredPane);
	}

	@Override
	public boolean validateDrag(DragItems dragItems) {
		super.validateDrag(dragItems);
		if (dragItems.getSize() > 0) {
			int location = dragItems.getCard().getParentLocation();
			if (location > 0) {
				Card card = dragItems.getDragParent().getCard(location - 1);
				if (card.getCardLabel().getShowFace())
					dragItems.setDragLevel(DragItems.DragLevel.BOTTOM_CARD_ONLY);	
				else dragItems.setDragLevel(DragItems.DragLevel.ENTIRE_STACK);
			}
			else dragItems.setDragLevel(DragItems.DragLevel.ENTIRE_STACK);
		}
		return true;
		// TODO Auto-generated method stub
	
	}

	@Override
	public boolean validateDrop(DragItems dragItems) {
		// TODO Auto-generated method stub

		if (dragItems.getDragLevel() == DragItems.DragLevel.BOTTOM_CARD_ONLY)
			return false;
		Card card = getCard();
		if (card == null) {
			if (dragItems.getCard().getNumber() == 12)   // is it a king ???
				return true;
			else return false;
		}
		else if (dragItems.getCard().getColor() != getCard().getColor() && (dragItems.getCard().getNumber() == (getCard().getNumber() - 1)))  // next card in sequence
			return true;
		else return false;
	}

	@Override
	public void mouseClick() {
		// TODO Auto-generated method stub

	}
}

