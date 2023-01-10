package de.woock.games.breakout.assets.artefacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import de.woock.games.breakout.items.AbstractDrawableItem;

public class PointsText extends AbstractDrawableItem{
	public String points ="00000";
	DecimalFormat format = new DecimalFormat(points);
	public Font font = new Font("Arial", Font.BOLD, 26);
	
	@Override
	public void draw(Graphics g) {
		xPos = 180;
		yPos = 20;
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString(" Player 1: " + points, (int)xPos, (int)yPos);
	}
		
	public void addPoints(int scoredPoints) {
		int sum = Integer.parseInt(points) + scoredPoints;
		points  = format.format(sum);
	}

	public int getPoints() {
		return Integer.parseInt(points);
	}
}
