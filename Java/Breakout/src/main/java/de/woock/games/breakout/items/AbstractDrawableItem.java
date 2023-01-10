package de.woock.games.breakout.items;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class AbstractDrawableItem implements DrawableItem {

	protected double    xPos;
	protected double    yPos;
	protected Image     image;
	protected Applet    applet;
	protected Rectangle shape;
	
	@Override
	public abstract void draw(Graphics g);
	
	public Rectangle getShape() {
		return shape;
	}
	
	public boolean hits(DrawableItem item) {
		return shape != null && item != null 
			&& item.getShape() != null
			&& shape.intersects(item.getShape());
	}
}