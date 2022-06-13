import java.util.HashMap;

import javax.swing.JLabel;

public class CardMap {

	static private HashMap<JLabel, Object> cardMap = new HashMap<JLabel, Object>();
	

	public static void put(JLabel label, Card card) {
		cardMap.put(label, card);
	}
	
	public static void put(JLabel label, CardStack cardStack) {
		cardMap.put(label, cardStack);
	}
	
	public static Object get(JLabel label) {
		return cardMap.get(label);
	}
}
