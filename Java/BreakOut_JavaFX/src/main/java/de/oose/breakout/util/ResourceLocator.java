package de.oose.breakout.util;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ResourceLocator {
    private static String imagePathWithJavaDir = "Java/BreakOut_JavaFX/src/main/java/de/oose/breakout/assets/images/";
    private static String imagePathWithoutJavaDir = "file:src/main/java/de/oose/breakout/assets/images/";

    private static String soundPathWithJavaDir = "Java/BreakOut_JavaFX/src/main/java/de/oose/breakout/assets/sounds/";
    private static String soundPathWithoutJavaDir = "file:src/main/java/de/oose/breakout/assets/sounds/";

    public static Image getImage(String name) {
        Path path = Paths.get(imagePathWithJavaDir + name);
        if(Files.exists(path)) {
            return new Image("file:" + imagePathWithJavaDir + name);
        }
        return new Image(imagePathWithoutJavaDir + name);
    }

    public static AudioClip getSound(String name) {
        Path path = Paths.get(soundPathWithJavaDir + name);
        if(Files.exists(path)) {
            return new AudioClip("file:" + soundPathWithJavaDir + name);
        }
        return new AudioClip(soundPathWithoutJavaDir + name);
    }
}
