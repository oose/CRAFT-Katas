package de.woock.games.breakout.listener;

import static de.woock.games.breakout.util.PlayerMove.left;
import static de.woock.games.breakout.util.PlayerMove.right;
import static de.woock.games.breakout.util.PlayerMove.stopped;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.woock.games.breakout.Breakout;

public class BreakoutKeyListener implements KeyListener {

	private Breakout breakout = null;
	
	public BreakoutKeyListener(Breakout breakout) {
		this.breakout = breakout;
	}

	@Override
	public void keyTyped(KeyEvent evt) {}

	@Override
	public void keyPressed(KeyEvent evt) {
		if      (evt.getKeyCode() == KeyEvent.VK_LEFT)  breakout.playerMove = left;
		else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) breakout.playerMove = right;
		else if (evt.getKeyCode() == KeyEvent.VK_SPACE) breakout.setBallRunning();
		else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			if(breakout.isBallRunning() && breakout.isShotActive()) {
				breakout.generateShots();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_LEFT 
	     || evt.getKeyCode() == KeyEvent.VK_RIGHT) breakout.playerMove = stopped;
	}

}
