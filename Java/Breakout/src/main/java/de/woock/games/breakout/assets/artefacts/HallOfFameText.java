package de.woock.games.breakout.assets.artefacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import de.woock.games.breakout.items.AbstractDrawableItem;

public class HallOfFameText extends AbstractDrawableItem {
	private String text = "00000";
	DecimalFormat format = new DecimalFormat(text);
	private Font font = new Font("Arial", Font.BOLD, 26);

	public HallOfFameText(String text, int xPos, int yPos) {
		this.text = text;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(text, (int) xPos, (int) yPos);
	}

	public void setPoints(String score) {
		text = score;
	}
}
