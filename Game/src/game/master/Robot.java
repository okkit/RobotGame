package game.master;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.okkit.GameRules_okkit;
import utils.BoundsUtils;

public class Robot extends GamePiece implements Mortable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer timer;
	int millisec;
	int step;
	int dieAfter;

	public Robot(int x, int y) {
		this(new Point(x, y));
	}

	public Robot(Point position) {
		this(position, 10, 500);
	}

	public Robot(Point position, int step, int millisec) {
		super(Constants.ROBOT_IMAGE1, position);
		this.millisec = millisec;
		this.step = step;
		this.dieAfter = 5;
	}

	public Timer getTimer() {
		return this.timer;
	}

	public void die() {

		Container parent = this.getParent();
		parent.remove(this);
		parent.repaint();
	}

	public void action() {

		Robot me = this;
		timer = new Timer(this.millisec, new ActionListener() {

			private int counter = 0;

			@Override
			public void actionPerformed(ActionEvent evt) {

				if ("DIE".equals(evt.getActionCommand())) {
					timer.stop();
					me.die();
					return;
				}
				
			
				Rectangle player = GamePlayer.getPlayer().getBounds();
				Rectangle my = me.getBounds();
				int step = me.step;

				int new_x = my.x;
				int new_y = my.y;

				int richtung_LR;
				int richtung_UO;

				if (my.y + my.height < player.y) {
					richtung_UO = Constants.UNTEN;
				} else {// if (my.y > player.y + player.height)
					richtung_UO = Constants.OBEN;
				}

				if (my.x + my.width < player.x) {
					richtung_LR = Constants.RECHTS;
				} else { // if (my.x > player.x + player.width)
					richtung_LR = Constants.LINKS;
				}
				
				GamePanel parent = (GamePanel) me.getParent();
				parent.willDoStep(me, richtung_UO, 30);


				if (counter == me.dieAfter) {
					((Timer) evt.getSource()).stop();
					me.die();
				} 
			}
		});
		timer.start();
	}
}
