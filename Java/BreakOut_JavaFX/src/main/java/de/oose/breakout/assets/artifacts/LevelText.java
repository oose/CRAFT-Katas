package de.oose.breakout.assets.artifacts;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class LevelText extends Text {
    private String level = "";
    public Font font = Font.font("Arial", FontWeight.BOLD, 20);

    public LevelText(int level) {
        super(20, 40, "Level: " + level);
        setLevel(level);
        setFont(font);
        setFill(Color.GREEN);
    }

    public void setLevel(int level) {
        this.level = String.valueOf(level);
    }
}
