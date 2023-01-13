package de.oose.breakout.assets.artifacts;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class HallOfFameText extends Text {
	private final int xPos;
	private final int yPos;
	private String text = "00000";
	DecimalFormat format = new DecimalFormat(text);
	private Font font = Font.font("Arial", FontWeight.BOLD, 26);

	public HallOfFameText(String text, int xPos, int yPos) {
		super(xPos, yPos, text);
		this.text = text;
		this.xPos = xPos;
		this.yPos = yPos;
		setFont(font);
		setFill(Color.GREEN);
	}

	public void setPoints(String score) {
		text = score;
	}
}
