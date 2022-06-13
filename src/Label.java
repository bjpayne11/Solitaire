
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class Label {

	private JLabel label;
	private Point origin;
	private boolean showFace;
	private ImageIcon cardBack;
	private ImageIcon cardFace;


	public Label(ImageIcon cardFace, ImageIcon cardBack, EventListener listener) {
		
		this(cardFace, cardBack, new Point(0, 0), listener, true);
	}
	

	public Label(ImageIcon cardFace, ImageIcon cardBack, Point origin, EventListener listener, boolean enabled) {
		
		this.cardBack = cardBack;
		this.cardFace = cardFace;
		label = new JLabel();
		if (showFace)
			label.setIcon(cardFace);
		else label.setIcon(cardBack);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
	    label.setBounds(origin.x, origin.y, 72, 96);
	    label.setEnabled(enabled);
	    label.addMouseListener((MouseListener) listener);
	    label.addMouseMotionListener((MouseMotionListener) listener);
	}


	public void setShowFace(boolean showFace) {
		this.showFace = showFace;
	}

	public boolean getShowFace() {
		return showFace;
	}
	

	public void setPoint(Point origin) {
		this.origin = origin;
		label.setBounds(origin.x, origin.y, 72, 96);
	}

	
	public void flipCard() {
		if (showFace) {
			label.setIcon(cardBack);
			showFace = false;
		}
		else {
			label.setIcon(cardFace);
			showFace = true;
		}
	}
	
	public void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
	}
	
}
