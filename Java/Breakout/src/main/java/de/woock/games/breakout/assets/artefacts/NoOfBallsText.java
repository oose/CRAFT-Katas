package de.woock.games.breakout.assets.artefacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.woock.games.breakout.items.AbstractDrawableItem;

public class NoOfBallsText extends AbstractDrawableItem {
	public int balls = 0;
	private String dots = "";
	public Font font = new Font("Arial", Font.BOLD, 26);

	public NoOfBallsText(int balls) {
		setNoOfBalls(balls);
	}

	@Override
	public void draw(Graphics g) {
		xPos = 20;
		yPos = 20;
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("Balls: " + dots, (int) xPos, (int) yPos);
	}

	public void setNoOfBalls(int balls) {
		dots = "";
		this.balls = balls;
		for (int i = 0; i < balls; i++) {
			dots += "o";
		}
	}

}
