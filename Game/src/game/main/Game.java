package game.main;

import game.GameFrame;
import game.GamePanel;

public class Game {

	public static void main(String[] args) {

		GameFrame m = new GameFrame();
		m.add(new GamePanel("Room1")).requestFocusInWindow();
		
		m.repaint();
	}

}
