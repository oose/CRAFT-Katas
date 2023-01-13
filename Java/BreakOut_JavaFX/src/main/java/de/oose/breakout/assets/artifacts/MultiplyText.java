package de.oose.breakout.assets.artifacts;

import de.oose.breakout.Breakout;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MultiplyText extends Text {
    private int multiply = 1;
    private StringProperty stringProperty = new SimpleStringProperty("");

    public MultiplyText(int multiply) {
        super(Breakout.fieldWidth - 46, 40, "");
        this.multiply = multiply;
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        setFont(font);
        setFill(Color.GREEN);
        setMultiplyer(multiply);
        textProperty().bind(stringProperty);
    }

    public void setMultiplyer(int mult) {
        this.multiply = mult;
        String multiplier = String.valueOf(mult);
        if (mult >= 1) {
            stringProperty.set("x" + multiplier);
        } else {
            stringProperty.set("");
        }
    }

}
