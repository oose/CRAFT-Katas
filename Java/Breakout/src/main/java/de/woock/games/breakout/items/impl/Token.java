package de.woock.games.breakout.items.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.woock.games.breakout.Breakout;
import de.woock.games.breakout.items.AbstractDrawableItem;

/**
 * @author woh
 * 
 */
public class Token extends AbstractDrawableItem {
	
	private Color  color;
	private String character;
	private int    points;

	private Font font = new Font("Arial", Font.BOLD, 15);

	private final int radius = 20;

	public Token(Color color, String character, int points) {
		this.color     = color;
		this.character = character;
		this.points    = points;
	}

	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	public void move(int speed) {
		yPos += speed;
	}

	@Override
	public void draw(Graphics g) {
		shape = new Rectangle((int) xPos, (int) yPos, radius, radius);

		g.setColor(color);
		g.fillOval((int) xPos, (int) yPos, radius, radius);

		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(character, (int) (xPos + radius / 3), (int) (yPos + radius * 3 / 4));
	}

	public String getCharacter() {
		return character;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isOutOfField() {
		return yPos > Breakout.fieldHight;
	}

	@Override
	public String toString() {
		return "Token [character=" + character + ", points=" + points + "]";
	}
}
