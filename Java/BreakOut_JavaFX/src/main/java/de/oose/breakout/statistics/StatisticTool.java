package de.oose.breakout.statistics;

import de.oose.breakout.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StatisticTool {

	Logger logger = LogManager.getLogger(getClass());

	final static Charset ENCODING = StandardCharsets.UTF_8;

	private List<String> fileOperation(String logFile) {
		Path path = Paths.get(logFile);
		List<String> logLines = new ArrayList<>();
		try {
			logLines = Files.readAllLines(path, ENCODING);
		} catch (IOException e) {
			throw new RuntimeException("Could not read Logfile from: " + logFile);
		}

		return logLines;
	}

	private List<String> readLogFileForNumberMethods() {
		List<String> logLines = fileOperation("breakoutLog.log");
		return logLines;
	}

	private int countLinesByString(List<String> logLines, String searchString) {
		int count = 0;
		for (String logEntry : logLines) {
			if (logEntry.contains(searchString)) {
				count+=1;
			}
		}
		return count;
	}

	public int getNumberOfHitTiles() {
		List<String> logLines = readLogFileForNumberMethods();
		int count = countLinesByString(logLines, "hit Tile");
		return count;
	}

	public int getNumberOfUsedBalls() {
		List<String> logLines = readLogFileForNumberMethods();
		int count = 1 + countLinesByString(logLines, "next Ball");
		return count;
	}

	public long getTime() {
		List<String> strings = fileOperation("breakoutLog.log");

		String string = strings.get(0);
		String date = StringUtils.extractTimeStringFromLogline(string);
		LocalDateTime start = convertDateStringToDate(date);
		long start2 = Timestamp.valueOf(start).getTime();

		String lastString = strings.get(strings.size() - 1);
		date = StringUtils.extractTimeStringFromLogline(lastString);
		LocalDateTime end = convertDateStringToDate(date);
		long end2 = Timestamp.valueOf(end).getTime();

		return (end2 - start2) / 1000 / 60;
	}

	public String getPrettyStatisticsString() {
		List<String> logLines = fileOperation("breakoutLog.log");

		// Number of Balls
		// Calculates Number of Balls from LogFiles
		// Every Line is checked, if it contains the Ballstring
		// if it does, c is increased
		int c = 1;

		for (int i = 0; i < logLines.size(); i++) {
			if (logLines.get(i).contains("next Ball")) {
				c++; // no pun intended
			}
		}

		return "Time played: " + getTime() + " Minutes\nTiles hit: " + getNumberOfHitTiles() + "\nBalls used: " + c;
	}

	public LocalDateTime convertDateStringToDate(String dateString) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
		LocalDateTime result = LocalDateTime.now();

		try {
			result = LocalDateTime.parse(dateString, dateTimeFormatter);
		} catch (Exception e1) {
			// TODO: Exceptionhandling
		}

		return result;

	}

}
