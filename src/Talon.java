import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;


public class Talon extends CardStack {
	
	public Talon(Point origin, JLayeredPane layeredPane) {
		super(origin, 7, 0, Display.DISPLAY_ALL_CARDS, CardDisplay.DISPLAY_FRONT_ONLY, RemovalOrder.LIFO, 
				DragOption.ONE_CARD, layeredPane);
	}

	@Override
	public boolean validateDrag(DragItems dragItems) {
		dragItems.setDragLevel(DragItems.DragLevel.ENTIRE_STACK);
		return true;
	}


	@Override
	public boolean validateDrop(DragItems dragItems) {
		return false;
	}

	@Override
	public void mouseClick() {
		// TODO Auto-generated method stub

	}
	
}

