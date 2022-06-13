
import java.awt.Point;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;


public class Stock extends CardStack {
	
	private CardStack talon;

	public Stock(Point origin, JLayeredPane layeredPane, CardStack talon) {
		super(origin, 0, 0, Display.DISPLAY_TOP_CARD_ONLY, CardDisplay.DISPLAY_BACK_ONLY, RemovalOrder.FIFO, 
				DragOption.NONE, layeredPane);
		
		this.talon = talon;
	}

	@Override
	public boolean validateDrag(DragItems dragItems) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateDrop(DragItems dragItems) {
		return false;
	}

	@Override
	public void mouseClick() {
		// TODO Auto-generated method stub
		int flipNumber = 3;
		flipNumber = Math.min(flipNumber,  size());
		if (flipNumber != 0) {
			for (int i=0; i<flipNumber; i++) {
				Card card = getCard();
				if (card != null) {
					talon.addCard(card);
					removeCard();
					if (size() == 0){
						getBlankCardLabel().getLabel().setEnabled(false);
						getBlankCardLabel().getLabel().setIcon(CardStack.getBlankCard());
					}
				}
			}
		}
		else {
			addCards(talon.getAllCards());
			if (size() > 0) {
				getBlankCardLabel().getLabel().setEnabled(true);
				getBlankCardLabel().getLabel().setIcon(Card.getCardBack());
			}
			talon.clear();
		}
	}
	
	
}
