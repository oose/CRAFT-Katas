package de.woock.games.breakout.highscore;

import java.util.Comparator;

public class HighScoreComparator implements Comparator<HighScore> {

	@Override
	public int compare(HighScore o1, HighScore o2) {
		return o1.getScore().compareTo(o2.getScore());
	}

}
