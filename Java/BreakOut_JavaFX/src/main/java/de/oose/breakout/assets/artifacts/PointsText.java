package de.oose.breakout.assets.artifacts;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class PointsText extends Text {
	public String points ="00000";
	DecimalFormat format = new DecimalFormat(points);
	StringProperty pointsProperty = new SimpleStringProperty(points);
	public Font font = Font.font("Arial", FontWeight.BOLD, 26);

	public PointsText() {
		super(180, 20, "");
		setFont(font);
		setFill(Color.GREEN);
		addPoints(0);
		textProperty().bind(pointsProperty);
	}

	public void addPoints(int scoredPoints) {
		int sum = Integer.parseInt(points) + scoredPoints;
		points  = format.format(sum);
		pointsProperty.set("Player 1: " + points);
	}

	public int getPoints() {
		return Integer.parseInt(points);
	}
}
