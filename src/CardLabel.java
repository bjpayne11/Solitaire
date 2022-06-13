import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class CardLabel {

	private JLabel label;
	private Point origin;

	private boolean showFace;
	private ImageIcon cardBack;
	private ImageIcon cardFace;


	public CardLabel(ImageIcon cardFace, ImageIcon cardBack) {
		
		this(cardFace, cardBack, new Point(0, 0), true);
	}
	

	public CardLabel(ImageIcon cardFace, ImageIcon cardBack, Point origin, boolean enabled) {
		
		this.showFace = showFace;
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
	}


	public void setShowFace(boolean showFace) {
		this.showFace = showFace;
	}

	public boolean getShowFace() {
		return showFace;
	}
	
	/*
	public void setPoint(Point origin) {
		this.origin = origin;
		label.setBounds(origin.x, origin.y, 72, 96);
	}
	*/

		
	
	public JLabel getLabel() { 	
		if (showFace)
			label.setIcon(cardFace);
		else label.setIcon(cardBack);
		return label;
	
	}

	
	public void flipCard() {
		label.setIcon(cardFace);
		showFace = true;
	}
	
	public void setEnabled(boolean enabled) {
		label.setEnabled(enabled);
	}
	
}
