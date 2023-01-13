package de.oose.breakout.items;

import java.applet.Applet;
import java.awt.*;

public abstract class AbstractDrawableItem implements DrawableItem {

	protected double    xPos;
	protected double    yPos;
	protected Image     image;
	protected Applet    applet;
	protected Rectangle shape;
	
	@Override
	public abstract void draw();
	
	public Rectangle getShape() {
		return shape;
	}
	
	public boolean hits(DrawableItem item) {
		return shape != null && item != null 
			&& item.getShape() != null
			&& shape.intersects(item.getShape());
	}
}