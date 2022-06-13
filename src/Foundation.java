import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class Foundation extends CardStack {

	private Card.Suits suit;

	public Foundation(Point origin, JLayeredPane layeredPane) {
		super(origin, 0, 0, Display.DISPLAY_TOP_CARD_ONLY, CardDisplay.DISPLAY_FRONT_ONLY, RemovalOrder.LIFO, 
				DragOption.NONE, layeredPane);
	}

	@Override
	public boolean validateDrag(DragItems dragItems) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateDrop(DragItems dragItems) {
		
		// TODO Auto-generated method stub
		if (dragItems.getSize() > 1)
			return false;
		else {
			Card card = getCard();
			if (card == null) {
				if (dragItems.getCard().getNumber() == 0)   // is it an ace???
					return true;
				else return false;
			}
			else if (dragItems.getCard().getSuit() == getCard().getSuit() && (dragItems.getCard().getNumber() == (getCard().getNumber() + 1)))  // next card in sequence
				return true;
			else return false;
		}
	}

	@Override
	public void mouseClick() {
		// TODO Auto-generated method stub

	}
}
	

