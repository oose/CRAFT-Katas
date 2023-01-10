package de.woock.games.breakout.assets.artefacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.woock.games.breakout.items.AbstractDrawableItem;

public class LevelText extends AbstractDrawableItem {
	private String level = "";
	public Font font = new Font("Arial", Font.BOLD, 20);

	public LevelText(int level) {
		setLevel(level);
	}

	@Override
	public void draw(Graphics g) {
		xPos = 20;
		yPos = 40;
		g.setFont(font);
		g.setColor(Color.GREEN);
		g.drawString("Level: " + level, (int) xPos, (int) yPos);
	}

	public void setLevel(int level) {
		this.level = String.valueOf(level);
	}
}
