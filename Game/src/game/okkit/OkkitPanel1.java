package game.okkit;

import game.GamePanel;

public class OkkitPanel1 extends GamePanel {

	public OkkitPanel1() {
		super("/Room1.txt");
	}
	
	public OkkitPanel1(String dataFile) {
		super(dataFile);
	}

	@Override
	protected boolean interaction(Object attacker, Object attacked) {
		return GameRules_okkit.interaction(attacker, attacked);
	}
	
	@Override
	protected boolean movable(Object moving, Object standing) {
		return GameRules_okkit.movable(moving, standing);
	}

}
