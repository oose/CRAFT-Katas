package de.woock.games.breakout.util;

import java.applet.AudioClip;
import java.awt.Image;

import de.woock.games.breakout.Breakout;

public class ResourceLocator {
	private static String imagePath = "de/woock/games/breakout/assets/images/";
	private static String soundPath = "de/woock/games/breakout/assets/sounds/";

	public static Image getImage(Breakout breakout, String name) {
		return breakout.getImage(breakout.getCodeBase(), imagePath + name);
	}
	
	public static AudioClip getSound(Breakout breakout, String name) {
		return breakout.getAudioClip(breakout.getCodeBase(), soundPath + name);
	}
}
