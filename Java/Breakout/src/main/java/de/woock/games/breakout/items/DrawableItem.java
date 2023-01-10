package de.woock.games.breakout.items;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface DrawableItem {

	public void draw(Graphics g);
	public Rectangle getShape();
}