package de.oose.breakout.highscore;

import java.util.List;

public interface HighScoreDAO {

	List<HighScore> getHighScores();

	void saveHighScore(int score, String name);

	HighScore getHighScoreByName(String name);
}
