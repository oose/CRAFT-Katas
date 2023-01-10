package de.woock.games.breakout.assets.artefacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.woock.games.breakout.Breakout;
import de.woock.games.breakout.items.AbstractDrawableItem;

public class MultiplyText extends AbstractDrawableItem {
	private String multiplier = "1";
	private Font font = new Font("Arial", Font.BOLD, 20);

	public MultiplyText() {
	}

	@Override
	public void draw(Graphics g) {
		xPos = Breakout.fieldWidth - 46;
		yPos = 40;
		g.setFont(font);
		g.setColor(Color.RED);
		if (multiplier.equals("1")) {
			g.drawString("x" + multiplier, (int) xPos, (int) yPos);
		} else {
			g.drawString("  ", (int) xPos, (int) yPos);
		}
	}

	public void setMultiplyer(int mult) {
		multiplier = String.valueOf(mult);
	}

}
