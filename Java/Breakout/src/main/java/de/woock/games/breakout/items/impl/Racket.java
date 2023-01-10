package de.woock.games.breakout.items.impl;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import de.woock.games.breakout.items.AbstractDrawableItem;
import de.woock.games.breakout.util.PlayerMove;


public class Racket extends AbstractDrawableItem{
	
	public Racket(Applet applet, Image image) {
		xPos        = applet.getBounds().width  / 2;
		yPos        = applet.getBounds().height - 50;
		this.image  = image;
		this.applet = applet;
		shape       = new Rectangle((int)xPos, (int)yPos, image.getWidth(applet), image.getHeight(applet));
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)xPos, (int)yPos, applet);
		shape = new Rectangle((int)xPos, (int)yPos, image.getWidth(applet), image.getHeight(applet));
	}
	
	public void moveX(PlayerMove playerMove) {
		int rightEnd = applet.getBounds().width - image.getWidth(applet);
		int leftEnd  = 0;
		boolean leftBorderOK  = xPos > leftEnd  || playerMove.getSpeed() > 0;
		boolean rightBorderOK = xPos < rightEnd || playerMove.getSpeed() < 0;
		
		if (leftBorderOK && rightBorderOK) {
			xPos += playerMove.getSpeed();
		}
	}

	public int getMiddleX(int radius) {
		return shape.x - radius / 2 + shape.width / 2;
	}

	public double getOnRacketY(int radius) {
		return shape.y - radius;
	}

	public int top() {
		return (int)yPos;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
