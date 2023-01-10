package de.woock.games.breakout.items.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.woock.games.breakout.items.AbstractDrawableItem;

public class Shot extends AbstractDrawableItem {

	private final int radius = 3;

	public Shot(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void move(int speed) {
		yPos += speed;
	}

	public boolean isOutOfField() {
		return yPos < 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		shape = new Rectangle((int) xPos, (int) yPos, radius, radius);
		g.fillOval((int) xPos, (int) yPos, radius, radius);
	}
}