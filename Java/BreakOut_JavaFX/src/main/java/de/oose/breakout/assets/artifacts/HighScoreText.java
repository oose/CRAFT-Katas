package de.oose.breakout.assets.artifacts;

import de.oose.breakout.highscore.HighScore;
import de.oose.breakout.highscore.HighScoreDAO;
import de.oose.breakout.highscore.impl.HighScoreDAOHibernate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.List;

public class HighScoreText extends Text {

	private List<HighScore> highScores;
	private String points = "00000";
	private StringProperty stringProperty = new SimpleStringProperty("Highscore: " + points);
	DecimalFormat format = new DecimalFormat(points);
	private Font font = Font.font("Arial", FontWeight.BOLD, 26);

	public HighScoreText(int scoredPoints) {
		super(400, 20, "");
		setFont(font);
		setFill(Color.GREEN);
		setPoints(scoredPoints);
		textProperty().bind(stringProperty);
		loadHighScore();
	}

	public void setPoints(int scoredPoints) {
		if (Integer.parseInt(points) < scoredPoints) {
			points = format.format(scoredPoints);
			stringProperty.set("Highscore: " + points);
		}
	}

	public List<HighScore> getHallOfFame() {
		return highScores;
	}

	private void loadHighScore() {
		HighScoreDAO dao = new HighScoreDAOHibernate();
		highScores = dao.getHighScores();
		if (highScores != null && highScores.size() > 0) {
			points = format.format(highScores.get(0).getScore());
		}
	}

	private void saveHighScore(int score, String name) {
		HighScoreDAO dao = new HighScoreDAOHibernate();
		dao.saveHighScore(score, name);
	}
}
