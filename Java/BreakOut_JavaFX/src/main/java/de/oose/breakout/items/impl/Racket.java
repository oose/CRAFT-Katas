package de.oose.breakout.items.impl;

import de.oose.breakout.Breakout;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class Racket extends ImageView {

    private Breakout breakout;
    private Rectangle shape;
    private int xPos;
    private int yPos;

    public Racket(Breakout breakout, Image image) {
        super(image);
        xPos = Breakout.fieldWidth / 2;
        yPos = breakout.fieldHight - 50;
        shape = new Rectangle((int) xPos, (int) yPos, (int) image.getWidth(), (int) image.getHeight());
        this.breakout = breakout;
    }

    public void moveRacketTo(double x, double y) {
        final double cx = getBoundsInLocal().getWidth() / 2;
        final double cy = getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= breakout.fieldWidth &&
                y - cy >= 0 &&
                y + cy <= breakout.fieldHight) {
            relocate(x - cx, y - cy);
        }
    }

    public Rectangle getShape() {
        return shape;
    }

    public double getMiddleX() {
        return getShape().getX();
    }

    public double getOnRacketY() {
        return getShape().getY() - getShape().getHeight() + 2;
    }

    public int top() {
        return (int) yPos;
    }

}
