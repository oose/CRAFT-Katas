package de.woock.games.breakout.util;

public enum PlayerMove {
	left(-3), stopped(0), right(3);
	
	private int speed = 0;
	
	private PlayerMove(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
}
