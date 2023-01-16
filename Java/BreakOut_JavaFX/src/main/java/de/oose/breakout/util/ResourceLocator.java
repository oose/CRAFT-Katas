package de.oose.breakout.util;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class ResourceLocator {
    private static String imagePath = "file:Java/BreakOut_JavaFX/src/main/java/de/oose/breakout/assets/images/";
    private static String soundPath = "file:Java/BreakOut_JavaFX/src/main/java/de/oose/breakout/assets/sounds/";

    public static Image getImage(String name) {
        return new Image(imagePath + name);
    }

    public static AudioClip getSound(String name) {
        return new AudioClip(soundPath + name);
    }
}
