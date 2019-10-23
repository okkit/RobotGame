package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Rectangle;

public class GameDoor extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String roomClassName;

	public GameDoor(int x, int y, int width, int height, String roomClassName) {
		super();
		setBounds(x, y, width, height);
		setBackground(new Color(33, 66, 99));
		this.roomClassName = roomClassName;
		
//		System.out.println("AM door to " + roomClassName);
	}

	public String pieceAtMe(Rectangle piece) {
		
		boolean yes = false;

//		// Bin eine Waagerechte Tür am oberen oder am unteren Rand des Raums
		if (this.getBounds().width > this.getBounds().getHeight()) {

			if (this.getBounds().y < 4)
				yes = piece.getBounds().y <= this.getBounds().height; // die Tür ist oben
			else
				yes = piece.getBounds().y + piece.getBounds().height + 10 > this.getBounds().y; // die Tür ist unten
		}

		// Bin eine Senkrechte Tür am linken oder am rechten Rand des Raums
		else {
			if (this.getBounds().x < 4)
				yes = piece.getBounds().x - 10 < this.getBounds().width; // die Tür ist oben
			else
				yes = piece.getBounds().x + piece.getBounds().width + 10 > this.getBounds().x; // die Tür ist unten
		}
		
		return yes ? this.roomClassName : null;
	}
}
