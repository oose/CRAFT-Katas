package de.oose.breakout.assets.artifacts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class NoOfBallsText extends Text {
    private String dots = "";
    private StringProperty sp = new SimpleStringProperty(dots);

    public int balls = 0;
    public Font font = Font.font("Arial", FontWeight.BOLD, 26);

    public NoOfBallsText(int balls) {
        super(20, 20, "");
        setNoOfBalls(balls);
        setFont(font);
        setFill(Color.GREEN);
        textProperty().bind(sp);
    }

    public void setNoOfBalls(int balls) {
        dots = "";
        this.balls = balls;
        for (int i = 0; i < balls; i++) {
            dots += "o";
        }
        sp.set("Balls:" + dots);
    }
}
