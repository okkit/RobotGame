package game.okkit;

import game.master.Constants;
import game.master.Robot;

public class GamePanel extends game.master.GamePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GamePanel(String dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	public GamePanel(String dataSource, int playerPosition) {
		super(dataSource, playerPosition);
		// TODO Auto-generated constructor stub
	}
	
	protected void initPieces(char[][] content) {
		
		super.initPieces(content);
		
		char ch;

		for (int i = 1; i < content.length - 3; i++) {
			for (int j = 1; j < content[i].length - 3; j++) {
				ch = content[i][j];
				if (ch == Constants.CH_ROBOT) {
					this.add(new Robot(j * Constants.FILE_STEP_X, i * Constants.FILE_STEP_Y));
				} 
			}
		}
	}

}
