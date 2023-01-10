package de.woock.games.breakout.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static String extractTimeStringFromLogline(String logLine) {
		String dateString = "";

		Pattern pattern = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d ");
		Matcher matcher = pattern.matcher(logLine);

		if (matcher.find()) {
			dateString = matcher.group();
		} else {
			throw new IllegalArgumentException("Could not extract Datestring from '" + logLine + "'");
		}
		return dateString.trim();
	}

}
