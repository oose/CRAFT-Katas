package de.oose.breakout.items.impl;

import de.oose.breakout.Breakout;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * @author woh
 * 
 */
public class Token extends Text {
	
	private Color color;
	private String character;
	private int    points;

	public Font font = Font.font("Arial", FontWeight.BOLD, 15);

	private final int radius = 20;
	private double xPos;
	private double yPos;

	public Token(Color color, String character, int points) {
		super(character);
		this.color     = color;
		this.character = character;
		this.points    = points;
		setFont(font);
		setFill(color);
	}

	public void setPosition(double x, double y) {
		this.xPos = x;
		this.yPos = y;
		setLayoutX(x);
		setLayoutY(y);
	}

	public void move(int speed) {
		yPos += speed;
		setLayoutY(getLayoutY() + speed);
	}

//	@Override
//	public void draw(Graphics g) {
//		shape = new Rectangle((int) xPos, (int) yPos, radius, radius);
//
//		g.setColor(color);
//		g.fillOval((int) xPos, (int) yPos, radius, radius);
//
//		g.setFont(font);
//		g.setColor(Color.BLACK);
//		g.drawString(character, (int) (xPos + radius / 3), (int) (yPos + radius * 3 / 4));
//	}

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
