package de.woock.games.breakout.assets.artefacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.List;

import de.woock.games.breakout.highscore.HighScore;
import de.woock.games.breakout.highscore.HighScoreDAO;
import de.woock.games.breakout.highscore.impl.HighScoreDAOHibernate;
import de.woock.games.breakout.items.AbstractDrawableItem;

public class HighScoreText extends AbstractDrawableItem {
	private List<HighScore> highScores;
	private String points = "00000";
	DecimalFormat format = new DecimalFormat(points);
	private Font font = new Font("Arial", Font.BOLD, 26);

	public HighScoreText() {
		loadHighScore();
	}

	@Override
	public void draw(Graphics g) {
		xPos = 400;
		yPos = 20;
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString(" Player 1: " + points, (int) xPos, (int) yPos);
	}

	public void setPoints(int scoredPoints) {
		if (Integer.parseInt(points) < scoredPoints) {
			points = format.format(scoredPoints);
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
